package com.justin.productcatalog.data.model

data class Product(
    val id: Int? = null,
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