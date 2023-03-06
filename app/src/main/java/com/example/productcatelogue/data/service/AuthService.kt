package com.example.productcatelogue.data.service

import com.example.productcatelogue.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class AuthService(private val auth:FirebaseAuth, private val ref:CollectionReference) {
    suspend fun createUser(user: User){
        val res=auth.createUserWithEmailAndPassword(user.email,user.pass).await()

        if(res.user!=null){
            ref.document(user.email).set(user).await()
//            ref.document(res.user!!.uid).set(user).await()
        }
    }

    suspend fun login(email:String,pass:String):Boolean{
        val res = auth.signInWithEmailAndPassword(email,pass).await()
        return res.user?.uid!=null
//        return res.user?.email?.let{
//            ref.document(it).get().await().toObject(User::class.java)
//        }
    }

    fun isAuthenticate():Boolean{
        val user = auth.currentUser
        if(user==null){
            return false
        }
        return true
    }

    fun deAuthenticate(){
        auth.signOut()
    }

    suspend fun getCurrentUser():User? {
        return auth.currentUser?.email?.let{
            ref.document(it).get().await().toObject(User::class.java)
        }
    }

}