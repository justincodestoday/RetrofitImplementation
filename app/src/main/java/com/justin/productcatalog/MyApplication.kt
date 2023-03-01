package com.justin.productcatalog

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application()
//{
////    val productRepository = ProductRepository.getInstance(RetrofitClient.getInstance())
//}