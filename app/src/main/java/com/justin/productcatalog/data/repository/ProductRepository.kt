package com.justin.productcatalog.data.repository

import com.justin.productcatalog.data.api.ProductApi
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.model.ProductsResponse
import retrofit2.Call
import retrofit2.Response

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts().products
    }

    suspend fun getProductById(id: Int): Product {
        return productApi.getProductById(id)
    }

    companion object {
        var productRepositoryInstance: ProductRepository? = null

        fun getInstance(productApi: ProductApi): ProductRepository {
            if (productRepositoryInstance == null) {
                productRepositoryInstance = ProductRepository(productApi)
            }

            return productRepositoryInstance!!
        }
    }
}