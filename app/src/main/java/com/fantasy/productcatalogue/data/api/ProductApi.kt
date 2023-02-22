package com.fantasy.productcatalogue.data.api

import com.fantasy.productcatalogue.data.model.ProductsResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse
}