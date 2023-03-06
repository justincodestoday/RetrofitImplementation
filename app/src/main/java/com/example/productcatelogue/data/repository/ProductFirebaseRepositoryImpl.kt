package com.example.productcatelogue.data.repository

import com.example.productcatelogue.data.model.Product
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class ProductFirebaseRepositoryImpl(private val ref: CollectionReference):ProductRepository {
    override suspend fun addProduct(product: Product) {
        ref.add(product.toHashMap()).await()
    }

    override suspend fun getAllProducts(): List<Product> {
        val products = mutableListOf<Product>()
        val res = ref.get().await()
        for (document in res) {
            products.add(document.toObject(Product::class.java).copy(id=document.id))
        }
        return products
    }

    override suspend fun updateProduct(id: String,product: Product): Product {
        val updatedProduct = product.copy(id=id)
        ref.document().set(updatedProduct.toHashMap()).await()
        return updatedProduct

    }
    override suspend fun getProductById(id: String): Product? {
        val res = ref.document(id).get().await()
        return res.toObject(Product::class.java)?.copy(id = id)
    }
    override suspend fun deleteProduct(id: String) {
        ref.document(id).delete().await()

    }
}