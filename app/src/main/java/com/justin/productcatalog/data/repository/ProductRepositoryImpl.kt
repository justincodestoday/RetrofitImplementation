package com.justin.productcatalog.data.repository

import com.justin.productcatalog.data.api.ProductApi
import com.justin.productcatalog.data.model.Product

class ProductRepositoryImpl(private val productApi: ProductApi): ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
//        return productApi.getAllProducts().products
        return productApi.getAllProducts()
    }

    override suspend fun getProductById(id: String): Product {
        return productApi.getProductById(id)
    }

    override suspend fun addProduct(product: Product) {
        return productApi.addProduct(product)
    }

    override suspend fun updateProduct(id: String, product: Product): Product {
        return productApi.updateProduct(id, product)
    }

    override suspend fun deleteProduct(id: String) {
        productApi.deleteProduct(id)
    }

//    companion object {
//        var productRepositoryImplInstance: ProductRepositoryImpl? = null
//
//        fun getInstance(productApi: ProductApi): ProductRepositoryImpl {
//            if (productRepositoryImplInstance == null) {
//                productRepositoryImplInstance = ProductRepositoryImpl(productApi)
//            }
//
//            return productRepositoryImplInstance!!
//        }
//    }
}