package com.example.productcatelogue.data.api


import com.example.productcatelogue.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("/products")
    suspend fun getAllProducts (): ProductResponse
}