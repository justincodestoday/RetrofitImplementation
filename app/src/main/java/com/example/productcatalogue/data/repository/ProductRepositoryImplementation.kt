package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.api.ProductApi
import com.example.productcatalogue.data.model.Product

class ProductRepositoryImplementation(private val productApi: ProductApi): ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts()
    }

    override suspend fun getProductById(id: String): Product {
        return productApi.getProductById(id)
    }

    override suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    override suspend fun editProduct(id: String, product: Product): Product {
        return productApi.editProduct(id, product)
    }

    override suspend fun deleteProduct(id: String) {
        return productApi.deleteProduct(id)
    }

//    companion object {
//        var productRepositoryInstance: ProductRepository? = null
//        fun getInstance(productApi: ProductApi): ProductRepository {
//            if (productRepositoryInstance == null) {
//                productRepositoryInstance = ProductRepository(productApi)
//            }
//            return productRepositoryInstance!!
//        }
//    }
}