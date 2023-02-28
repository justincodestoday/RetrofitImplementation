package com.justin.productcatalog.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String? = null,
    val brand: String,
    val category: String,
    val title: String,
    val description: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val thumbnail: String,
    val images: List<String>? = null
)