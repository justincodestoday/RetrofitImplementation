package com.example.productcatelogue

import android.app.Application
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
//    val productRepository=ProductRepository.getInstance(RetrofitClient.getInstance())
}