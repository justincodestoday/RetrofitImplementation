package com.justin.productcatalog.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
//    private const val baseUrl = "https://dummyjson.com"
//    const val baseUrl = "https://63fc15ce6deb8bdb814df698.mockapi.io/"
//    const val baseUrl = "http://10.1.104.31:5000"
    const val baseUrl = "https://product-catalog-backend.onrender.com"
//    private var retrofitInstance: ProductApi? = null
//
//    fun getInstance(): ProductApi {
//        if (retrofitInstance == null) {
//            retrofitInstance = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ProductApi::class.java)
//        }
//
//        return retrofitInstance!!
//    }
}