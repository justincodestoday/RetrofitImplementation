package com.justin.productcatalog.di

import com.justin.productcatalog.data.api.ProductApi
import com.justin.productcatalog.data.api.RetrofitClient
import com.justin.productcatalog.data.repository.ProductRepository
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

// di refers to Dependency Injection
// Singleton is also used to have only one distinct object instead of multiple duplicates of the same object
@Module
@InstallIn(SingletonComponent::class)
// it will be aligned with the whole application. This does not mean this is a singleton class.
object AppDependency {
    private val logger = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient().newBuilder().addInterceptor(logger)
        .addInterceptor(
            Interceptor {
                val builder = it.request().newBuilder()
                builder.addHeader("X-Auth", "qweasdzxc")
                return@Interceptor it.proceed(builder.build())
            }
        ).build()

    @Provides
    @Singleton
    @Named("greeting")
    fun greeting(): String {
        return "Welcome to Dagger Hilt 2"
    }

    @Provides
    @Singleton
    @Named("welcome")
    fun welcome(): String {
        return "Welcome back"
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