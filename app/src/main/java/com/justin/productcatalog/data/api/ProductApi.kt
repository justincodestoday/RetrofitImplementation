package com.justin.productcatalog.data.api

import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.model.ProductsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}