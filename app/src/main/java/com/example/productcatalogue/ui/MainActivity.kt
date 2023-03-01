package com.example.productcatalogue.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.productcatalogue.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    @Named("Greeting")
    lateinit var greeting: String

    @Inject
    @Named("Welcome")
    lateinit var welcome: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("debugging", greeting)
        Log.d("debugging", welcome)

        val db = Firebase.firestore

        // Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//
//// Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d("debugging", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("debugging", "Error adding document", e)
//            }


        //ReadData
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("debugging", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("debugging", "Error getting documents.", exception)
            }


    }
}