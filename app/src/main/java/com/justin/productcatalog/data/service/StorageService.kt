package com.justin.productcatalog.data.service

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.justin.productcatalog.data.model.Cart
import kotlinx.coroutines.tasks.await

object StorageService {
    private val ref = FirebaseStorage.getInstance().getReference("images/")
    fun addImage(uri: Uri, fileName: String, callback: (status: Boolean) -> Unit) {
        ref.child(fileName).putFile(uri)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun getImageUri(fileName: String, callback: (uri: Uri) -> Unit) {
        ref.child(fileName).downloadUrl.addOnSuccessListener {
            callback(it)
        }
    }

    fun deleteImage(oldImageName: String, callback: (status: Boolean) -> Unit) {
//        val storageRef = Firebase.storage.reference
//        val thumbnailRef = storageRef.child("images/$oldImageName")
//        thumbnailRef.downloadUrl.addOnSuccessListener {
//            thumbnailRef.delete().addOnSuccessListener {
//                callback(true)
//            }.addOnFailureListener {
//                callback(false)
//            }
//        }.addOnFailureListener {
//            callback(false)
//        }

        ref.child(oldImageName).downloadUrl
            .addOnSuccessListener {
                ref.child(oldImageName).delete()
                Log.d("debugging", "worked")
                callback(true)
            }
            .addOnFailureListener {
                Log.d("debugging", "did not work")
                callback(false)
            }
    }
}