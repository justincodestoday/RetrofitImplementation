package com.fantasy.productcatalogue.data.api

import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse

    @GET("/products/{id}")
    suspend fun getProductsById(@Path("id")id: Int): Product
}