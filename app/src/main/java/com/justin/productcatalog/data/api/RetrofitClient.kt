package com.justin.productcatalog.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
//    private const val baseUrl = "https://dummyjson.com"
    private const val baseUrl = "https://63fc15ce6deb8bdb814df698.mockapi.io/"
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