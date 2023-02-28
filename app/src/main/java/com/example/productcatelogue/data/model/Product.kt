package com.example.productcatelogue.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    @SerializedName("_id")
    val id: String?,
    val images: List<String>?=null ,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)