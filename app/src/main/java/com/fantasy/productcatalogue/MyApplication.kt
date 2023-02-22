package com.fantasy.productcatalogue

import android.app.Application
import com.fantasy.productcatalogue.data.api.RetrofitClient
import com.fantasy.productcatalogue.data.repository.ProductRepository

class MyApplication: Application() {
    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
}