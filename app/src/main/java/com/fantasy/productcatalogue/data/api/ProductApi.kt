package com.fantasy.productcatalogue.data.api

import com.fantasy.productcatalogue.data.model.Product
import retrofit2.http.*

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): List<Product>

    @GET("/products/{id}")
    suspend fun getProductsById(@Path("id")id: Int): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product)

    @PUT("/products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Product
}