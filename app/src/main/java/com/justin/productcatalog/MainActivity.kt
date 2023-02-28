package com.justin.productcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    @Inject // use only inject if there is only one dependency to inject
//    lateinit var greeting: String

    @Inject
    @Named("greeting")
    lateinit var greeting: String

    @Inject
    @Named("welcome")
    lateinit var welcome: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("debug", "$greeting and $welcome")
    }
}