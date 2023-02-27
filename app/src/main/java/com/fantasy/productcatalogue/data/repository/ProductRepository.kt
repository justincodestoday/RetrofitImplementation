package com.fantasy.productcatalogue.data.repository

import com.fantasy.productcatalogue.data.api.ProductApi
import com.fantasy.productcatalogue.data.model.Product

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts()
    }

    suspend fun getProductsById(id: Int): Product {
        return productApi.getProductsById(id)
    }

    suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    suspend fun updateProduct(id: Int, product: Product): Product {
        return productApi.updateProduct(id, product)
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