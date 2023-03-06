package com.example.productcatelogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.productcatelogue.data.service.AuthService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var authRepo: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!authRepo.isAuthenticate()){
            findNavController(R.id.navHostFragment).navigate(R.id.loginFragment2)
        }
    }
}