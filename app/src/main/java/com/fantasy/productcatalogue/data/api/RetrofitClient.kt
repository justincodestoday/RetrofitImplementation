package com.fantasy.productcatalogue.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://dummyjson.com"
    private var retrofitInstance: ProductApi? = null

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