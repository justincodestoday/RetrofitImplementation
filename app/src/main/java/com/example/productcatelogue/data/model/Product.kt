package com.example.productcatelogue.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val brand: String="",
    val category: String="",
    val description: String="",
    val discountPercentage: Double=0.00,
    @SerializedName("_id")
    val id: String?=null,
    val images: List<String>?=null ,
    val price: Int=0,
    val rating: Double=0.00,
    val stock: Int=0,
    val thumbnail: String="",
    val title: String=""
)
{
    fun toHashMap():HashMap<String,Any>{
        return hashMapOf(
            "title" to title,
            "brand" to brand,
            "category" to category,
            "description" to description,
            "thumbnail" to thumbnail,
            "discountPercentage" to discountPercentage,
            "price" to price,
            "rating" to rating,
            "stock" to stock,
        )
    }
}