package com.example.productcatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.productcatalogue.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    @Named("greeting")
    lateinit var greeting: String

    @Inject
    @Named("welcome")
    lateinit var welcome: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("debugging", greeting)
        Log.d("debugging", welcome)
    }
}