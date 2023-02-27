package com.justin.productcatalog.data.api

import com.justin.productcatalog.data.model.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/products")
//    suspend fun getAllProducts(): ProductsResponse
    suspend fun getAllProducts(): List<Product>

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product)

    @PUT("/products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Product
}