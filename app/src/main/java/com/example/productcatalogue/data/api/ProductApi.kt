package com.example.productcatalogue.data.api

import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.model.ProductsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): MutableList<Product>

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") searchById: Int
    ): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product)

    @PUT("/products/{id}")
    suspend fun editProduct(
        @Path("id") searchById: Int,
        @Body product: Product
    ): Product
}