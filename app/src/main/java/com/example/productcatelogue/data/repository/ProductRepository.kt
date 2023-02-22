package com.example.productcatelogue.data.repository

import com.example.productcatelogue.data.api.ProductApi
import com.example.productcatelogue.data.model.Product

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts().products
    }

    companion object {

        private var productRepositoryInstance: ProductRepository? = null

        fun getInstance(productApi: ProductApi): ProductRepository {
            if (productRepositoryInstance == null) {
                productRepositoryInstance = ProductRepository(productApi)
            }
            return productRepositoryInstance!!
        }
    }
}