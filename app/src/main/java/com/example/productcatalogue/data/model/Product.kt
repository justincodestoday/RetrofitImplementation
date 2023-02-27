package com.example.productcatalogue.data.model

data class Product(
    val id: Int?,
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