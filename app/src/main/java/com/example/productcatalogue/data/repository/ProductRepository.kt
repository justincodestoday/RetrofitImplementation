package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>

    suspend fun getProductById(id: String): Product?

    suspend fun addProduct(product: Product)

    suspend fun deleteProduct(id: String)

    suspend fun editProduct(id: String, product: Product): Product?
}