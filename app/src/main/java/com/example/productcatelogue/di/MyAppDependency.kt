package com.example.productcatelogue.di

import com.example.productcatelogue.data.api.ProductApi
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.service.AuthService
import com.example.productcatelogue.data.repository.ProductFirebaseRepositoryImpl
import com.example.productcatelogue.data.repository.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyAppDependency {
    private val logger = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient().newBuilder().addInterceptor(logger).addInterceptor(
        Interceptor{
            val builder = it.request().newBuilder()
            builder.addHeader("X-Auth","isdsi")
            return@Interceptor it.proceed(builder.build())
        }
    ).build()

    @Provides
    @Singleton
    @Named("greeting")
    fun greeting():String{
     return "Welcome to Dagger Hilt 2"
    }
    @Provides
    @Singleton
    @Named("greeting2")
    fun greeting2():String{
     return "Welcome2 to Dagger Hilt 2"
    }

    @Provides
    @Singleton
    fun getRetrofitClient():ProductApi{
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ProductApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun getProductRepository(productApi:ProductApi):ProductRepository{
//        return ProductRepositoryImpl(productApi)
//    }

    @Provides
    @Singleton
    fun getFireStore():FirebaseFirestore{
        return Firebase.firestore
    }
    @Provides
    @Singleton
    fun getFireStoreProductRepository(db:FirebaseFirestore):ProductRepository{
        return ProductFirebaseRepositoryImpl(db.collection("products"))
    }

    @Provides
    @Singleton
    fun getFirebaseAuth():FirebaseAuth{
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun getAuthRepository(auth:FirebaseAuth,db:FirebaseFirestore): AuthService {
        return AuthService(auth,db.collection("user"))
    }
}