package com.example.productcatelogue.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    val baseUrl= "https://63fc158b6ecb7e3702b32700.mockapi.io"
//    val baseUrl= "mongodb+srv://chiaching21l:19OixkIStU58r0U8@mobileproductcluster.9j9bpmy.mongodb.net/MobileProduct?retryWrites=true&w=majority"
    val baseUrl= "http://10.1.104.20:5000"
    var retrofitInstance:ProductApi? = null

    fun getInstance():ProductApi{
        if(retrofitInstance==null){
            retrofitInstance= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApi::class.java)
        }
        return retrofitInstance!!
    }
}