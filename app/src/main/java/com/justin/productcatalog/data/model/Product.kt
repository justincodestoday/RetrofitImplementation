package com.justin.productcatalog.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String? = null,
    val brand: String? = null,
    val category: String? = null,
    val title: String? = null,
    val description: String? = null,
    val price: Float? = 0f,
    val discountPercentage: Float? = 0f,
    val rating: Float? = 0f,
    val stock: Int? = 0,
    val thumbnail: String? = null,
    val images: List<String>? = null
) {
    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "brand" to brand,
            "category" to category,
            "title" to title,
            "description" to description,
            "price" to price,
            "discountPercentage" to discountPercentage,
            "rating" to rating,
            "stock" to stock
        )
    }
}