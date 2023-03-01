package com.example.productcatalogue.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String?,
    val title: String,
    val brand: String,
    val category: String,
    val description: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val thumbnail: String,
    val images: List<String>? = null,
)
//{
//        fun toHashMap(): HashMap<String, Any> {
//            return hashMapOf(
//                "title" to title,
//                "brand" to brand,
//                "category" to category,
//                "description" to description,
//                "price" to price,
//                "discountPercentage" to discountPercentage,
//                "rating" to rating,
//                "stock" to stock,
//                "thumbnail" to thumbnail,
//            )
//        }
//    }
