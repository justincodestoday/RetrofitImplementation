package com.fantasy.productcatalogue.data.model

data class Product(
    val id: Int?,
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