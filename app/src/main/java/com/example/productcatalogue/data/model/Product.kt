package com.example.productcatalogue.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    val brand: String = "",
    val category: String = "",
    val price: Float = 0f,
    val stock: Int = 0,
    val discountPercentage: Float = 0f,
    val rating: Float = 0f,
    val thumbnail: String = "",
    val images: List<String>? = null,
) {
    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "title" to title,
            "description" to description,
            "brand" to brand,
            "category" to category,
            "price" to price,
            "stock" to stock,
            "discountPercentage" to discountPercentage,
            "rating" to rating,
            "thumbnail" to thumbnail,
        )
    }
}

/*
title
description
brand
category
price
stock
 */