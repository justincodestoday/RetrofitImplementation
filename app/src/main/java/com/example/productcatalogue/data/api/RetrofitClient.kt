package com.example.productcatalogue.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val baseUrl = "https://dummyjson.compoi"
    var retrofitInstance: ProductApi? = null

    fun getInstance(): ProductApi {
        if (retrofitInstance == null) {
            retrofitInstance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApi::class.java)
        }
        return retrofitInstance!!
    }
}