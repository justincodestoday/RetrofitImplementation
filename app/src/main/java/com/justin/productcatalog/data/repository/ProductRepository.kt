package com.justin.productcatalog.data.repository

import com.justin.productcatalog.data.model.DeptWithStudent
import com.justin.productcatalog.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>

    suspend fun getProductById(id: String): Product?

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(id: String, product: Product): Product?

    suspend fun deleteProduct(id: String)

    suspend fun addDummy(dummy: DeptWithStudent)
}