package com.example.productcatelogue.data.repository

import android.util.Log
import com.example.productcatelogue.data.api.ProductApi
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.model.ProductsResponse

class ProductRepository(private val productApi:ProductApi) {
    suspend fun getAllProducts():List<Product>{
        Log.d("ewqewqewq","hello")
        return productApi.getAllProducts()
    }
    suspend fun getProductById(id:String):Product{
        return productApi.getProductById(id)
    }
    suspend fun addProduct(product:Product){
        return productApi.addProduct(product)
    }
    suspend fun updateProduct(id: String, product: Product): Product {
        return productApi.updateProduct(id, product)
    }
    suspend fun deleteProduct(id:String){
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