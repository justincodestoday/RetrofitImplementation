package com.example.productcatelogue.data.api

import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts():ProductsResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id:Int):Product
}