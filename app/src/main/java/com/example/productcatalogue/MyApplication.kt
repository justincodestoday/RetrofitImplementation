package com.example.productcatalogue

import android.app.Application
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository

class MyApplication: Application() {
    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
}