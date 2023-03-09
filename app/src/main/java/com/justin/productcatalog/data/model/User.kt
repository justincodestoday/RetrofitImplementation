package com.justin.productcatalog.data.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val cart: List<Product>? = null
)
