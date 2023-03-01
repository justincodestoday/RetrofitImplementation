package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.api.ProductApi
import com.example.productcatalogue.data.model.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductRepository(private val productApi: ProductApi) {

    suspend fun getAllProducts(): List<Product> {
        return productApi.getAllProducts()
    }

    suspend fun getProductById(id: String): Product{
        return productApi.getProductById(id)
    }

    suspend fun addProduct(product: Product){
        return productApi.addProduct(product)
    }

    suspend fun updateProduct(id:String,product: Product):Product{
        return productApi.updateProduct(id,product)
    }

    suspend fun deleteProduct(id:String){
        return productApi.deleteProduct(id)
    }

//    companion object {
//        var productRepositoryInstance: ProductRepository? = null
//
//        fun getInstance(productApi: ProductApi): ProductRepository {
//            if (productRepositoryInstance == null) {
//                productRepositoryInstance = ProductRepository(productApi)
//            }
//
//            return productRepositoryInstance!!
//        }
//    }
}