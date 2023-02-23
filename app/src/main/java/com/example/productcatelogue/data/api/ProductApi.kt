package com.example.productcatelogue.data.api


import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/products")
    suspend fun getAllProducts(): ProductResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}