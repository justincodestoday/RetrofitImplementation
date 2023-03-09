package com.justin.productcatalog.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.justin.productcatalog.data.model.Cart
import com.justin.productcatalog.data.model.CartItem
import kotlinx.coroutines.tasks.await

class FireStoreCartRepository {
    private val ref = Firebase.firestore.collection("carts")

    suspend fun addProductToCart(uid: String, cartItem: CartItem) {
        // A DocumentSnapshot contains data read from a document in your Cloud Firestore database.
        // The data can be extracted with the getData or get methods.
        val doc = ref.document(uid).get().await()
        if (!doc.exists()) {
            ref.document(uid).set(Cart(listOf(cartItem))).await()
        } else {
            // toObject is to get Firebase to convert the data fetched to the class object in Kotlin
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