package com.justin.productcatalog.data.model

import com.google.firebase.firestore.DocumentId
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
    val images: List<String> = listOf("image1", "image2"),
    val image: Image = Image("image", "path"),
) {
//    fun toHashMap(): HashMap<String, Any?> {
//        return hashMapOf(
//            "id" to id,
//            "brand" to brand,
//            "category" to category,
//            "title" to title,
//            "description" to description,
//            "price" to price,
//            "discountPercentage" to discountPercentage,
//            "rating" to rating,
//            "stock" to stock
//        )
//    }
}

data class Image(
    val name: String = "",
    val path: String = ""
)