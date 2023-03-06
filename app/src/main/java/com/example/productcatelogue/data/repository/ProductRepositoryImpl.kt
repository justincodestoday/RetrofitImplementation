package com.example.productcatelogue.data.repository

import com.example.productcatelogue.data.api.ProductApi
import com.example.productcatelogue.data.model.Product

class ProductRepositoryImpl(private val productApi: ProductApi):ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
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
//
    override suspend fun deleteProduct(id: String) {
        return productApi.deleteProduct(id)
    }
//    companion object{
//        var productRepositoryInstance:ProductRepository?=null
//        fun getInstance(productApi: ProductApi):ProductRepository{
//            if(productRepositoryInstance==null){
//                productRepositoryInstance= ProductRepository(productApi)
//            }
//            return productRepositoryInstance!!
//        }
//    }


}