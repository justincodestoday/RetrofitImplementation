package com.example.productcatalogue.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    const val baseUrl = "https://63fc158a1ff79e133297a795.mockapi.io/"
//    const val baseUrl = "http://10.1.104.25:5000"
    const val baseUrl = "https://mobileproductcatalogueserver.onrender.com"

//    private var retrofitInstance: ProductApi? = null


//    fun getInstance(): ProductApi {
//        if(retrofitInstance == null){
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