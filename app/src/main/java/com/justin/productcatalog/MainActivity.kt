package com.justin.productcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.justin.productcatalog.data.service.AuthService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var authService: AuthService

//    @Inject // use only inject if there is only one dependency to inject
//    lateinit var greeting: String

    //    @Inject
//    @Named("greeting")
//    lateinit var greeting: String
//
//    @Inject
//    @Named("welcome")
//    lateinit var welcome: String

    //        Log.d("debug", "$greeting and $welcome")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!authService.isAuthenticated()) {
            findNavController(R.id.navHostFragment).navigate(R.id.toSignInFragment)
        } else {
            findNavController(R.id.navHostFragment).navigate(R.id.toHomeFragment)
        }

//        val db = Firebase.firestore

//        // Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Alan",
//            "middle" to "Mathison",
//            "last" to "Turing",
//            "born" to 1912
//        )
//
//        // Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d("debugging", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("debugging", "Error adding document", e)
//            }

//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d("debugging", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("debugging", "Error getting documents.", exception)
//            }
    }
}