package com.example.productcatelogue

import android.app.Application
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.repository.ProductRepository

class MyApplication:Application() {
    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
}