package com.justin.productcatalog.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.justin.productcatalog.data.model.DeptWithStudent
import com.justin.productcatalog.data.model.Product
import kotlinx.coroutines.tasks.await

class FireStoreProductRepository(private val ref: CollectionReference) : ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
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

    override suspend fun addProduct(product: Product) {
        ref.add(product).await()
    }

    override suspend fun updateProduct(id: String, product: Product): Product {
        val updatedProduct = product.copy(id = id)
        ref.document(id).set(updatedProduct).await()
        return updatedProduct
    }

    override suspend fun deleteProduct(id: String) {
        ref.document(id).delete().await()
    }

    override suspend fun addDummy(dummy: DeptWithStudent) {
        ref.add(dummy).await()
    }
}