package com.justin.productcatalog.data.repository

import com.justin.productcatalog.data.api.ProductApi
import com.justin.productcatalog.data.model.Product

class ProductRepository(private val productApi: ProductApi) {
    suspend fun getAllProducts(): List<Product> {
//        return productApi.getAllProducts().products
        return productApi.getAllProducts()
    }

    suspend fun getProductById(id: String): Product {
        return productApi.getProductById(id)
    }

    suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    suspend fun updateProduct(id: String, product: Product): Product {
        return productApi.updateProduct(id, product)
    }

    suspend fun deleteProduct(id: String): Product {
        return productApi.deleteProduct(id)
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