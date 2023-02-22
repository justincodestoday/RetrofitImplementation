package com.example.productcatelogue.data.api

import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.model.ProductsResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts():ProductsResponse
}