package com.example.productcatalogue.data.api

import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.model.ProductsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): List<Product>

    @GET("/product/{id}")
    suspend fun getProductById(@Path("id") id:Int): Product

    @POST("/products")
    suspend fun addProduct(@Body product: Product)


}