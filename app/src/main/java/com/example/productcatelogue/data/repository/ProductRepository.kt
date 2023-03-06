package com.example.productcatelogue.data.repository

import com.example.productcatelogue.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts():List<Product>
    suspend fun getProductById(id:String):Product?
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(id:String,product: Product):Product
    suspend fun deleteProduct(id:String)
}