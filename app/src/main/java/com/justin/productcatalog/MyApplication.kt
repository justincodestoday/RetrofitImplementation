package com.justin.productcatalog

import android.app.Application
import com.justin.productcatalog.data.api.RetrofitClient
import com.justin.productcatalog.data.repository.ProductRepository

class MyApplication: Application() {
    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
}