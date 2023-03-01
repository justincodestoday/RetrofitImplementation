package com.example.productcatalogue.data.repository

import com.example.productcatalogue.data.api.ProductApi
import com.example.productcatalogue.data.model.Product
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductRepositoryFirebase (private val productApi: ProductApi) {

    val db = Firebase.firestore

    suspend fun addProduct(product: Product){
        db.collection("products")
            .add(product)
        return productApi.addProduct(product)
    }

    suspend fun getAllProducts(): MutableList<QueryDocumentSnapshot> {
         val data = db.collection("products")
            .get()
            .result
            .toMutableList()

        return data!!
    }

    suspend fun getProductById(id: String): Product{
        return productApi.getProductById(id)
    }

    suspend fun updateProduct(id:String,product: Product):Product{
        return productApi.updateProduct(id,product)
    }

}