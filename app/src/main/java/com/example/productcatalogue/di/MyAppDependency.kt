package com.example.productcatalogue.di

import com.example.productcatalogue.data.api.ProductApi
import com.example.productcatalogue.data.api.RetrofitClient
import com.example.productcatalogue.data.repository.ProductRepository
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
@InstallIn(SingletonComponent::class) //align for whole application
object MyAppDependency {
    private val logger = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(logger)
        .addInterceptor(
            Interceptor {
                val builder = it.request().newBuilder()
                return@Interceptor it.proceed(builder.build())
            }
        ).build()


    @Provides
    @Singleton
    @Named("Greeting")
    fun greeting(): String {
        return "Welcome to Dagger Hilt 2"
    }

    @Provides
    @Singleton
    @Named("Welcome")
    fun welcome(): String {
        return "I'm done"
    }

    @Provides
    @Singleton
    fun getRetrofitClient(): ProductApi {
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun getProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepository(productApi)
    }
}