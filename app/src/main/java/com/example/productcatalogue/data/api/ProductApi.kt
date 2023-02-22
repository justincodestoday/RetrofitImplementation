package com.example.productcatalogue.data.api

import com.example.productcatalogue.data.model.ProductsResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse

//    @GET("/products/{id}")
//    suspend fun getProductById(
//        @Path("id") searchById: Int
//    ): Product
}