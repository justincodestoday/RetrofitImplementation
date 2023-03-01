package com.example.productcatalogue

import android.app.Application
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application()