package com.example.productcatalogue.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String?,
    val title: String,
    val description: String,
    val brand: String,
    val category: String,
    val price: Float,
    val stock: Int,
    val discountPercentage: Float,
    val rating: Float,
    val thumbnail: String,
    val images: List<String>? = null,
)

/*
title
description
brand
category
price
stock
 */