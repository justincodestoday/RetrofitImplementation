package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.model.Product
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class FireStoreProductRepository(private val ref: CollectionReference) : ProductRepository {

    override suspend fun addProduct(product: Product) {
        ref.add(product.toHashMap()).await()
    }

    override suspend fun deleteProduct(id: String) {
        ref.document(id).delete().await()
    }

    override suspend fun editProduct(id: String, product: Product): Product {
        val update = product.copy(id = id)
        ref.document(id).set(update.toHashMap()).await()
        return update
    }

    override suspend fun getAllProducts(): MutableList<Product> {
        val products = mutableListOf<Product>()
        val res = ref.get().await()
        for (document in res) {
            products.add(document.toObject(Product::class.java).copy(id = document.id))
        }
        return products
    }

    override suspend fun getProductById(id: String): Product? {
        val res = ref.document(id).get().await()
        return res.toObject(Product::class.java)?.copy(id = id)
    }
}

//class FireStoreProductRepository(private val ref: CollectionReference) {
//    fun addProduct(product: Product, callback: (Boolean) -> Unit) {
//        ref.add(product)
//            .addOnSuccessListener {
//                callback(true)
//            }.addOnFailureListener {
//                callback(false)
//            }
//    }
//
//    fun getProduct(callback: (products: List<Product>) -> Unit) {
//        ref.get()
//    }
//}