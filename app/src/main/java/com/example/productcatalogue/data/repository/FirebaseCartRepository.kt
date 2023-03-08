package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.model.Cart
import com.example.productcatalogue.data.model.CartItem
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseCartRepository {
    private val ref = Firebase.firestore.collection("carts")

    suspend fun addProductToCart(uid: String, cartItem: CartItem) {
        val doc = ref.document(uid).get().await()

        if (!doc.exists()) {
            ref.document(uid).set(Cart(listOf(cartItem))).await()
        } else {
            val item = ref.document(uid).get().await().toObject(Cart::class.java)?.items?.find {
                it.id == cartItem.id
            }
            if (item != null) {
                ref.document(uid).update("items", FieldValue.arrayRemove(item))
                ref.document(uid)
                    .update("items", FieldValue.arrayUnion(cartItem.copy(count = item.count + 1)))
            } else {
                ref.document(uid).update("items", FieldValue.arrayUnion(cartItem)).await()
            }
        }
    }
}