package com.connect.androidinternassignment3.repository

import com.connect.androidinternassignment3.model.UserDetails
import com.connect.androidinternassignment3.utils.AuthListener
import com.google.firebase.auth.FirebaseAuth

class LoginRepository(authListener: AuthListener) {

    private var authListener:AuthListener? = null
    private val firebaseAuth = FirebaseAuth.getInstance()

    init {
        this.authListener = authListener
    }

    fun register(userDetails: UserDetails){
        firebaseAuth.createUserWithEmailAndPassword(userDetails.email,userDetails.password)
            .addOnCompleteListener{ task ->
            if (task.isSuccessful){
                firebaseAuth.signOut()
                authListener?.onSuccess("Registration Successful")
            }else{
                authListener?.onFailed(task.exception.toString())
            }
        }
    }

    fun login(email:String,password:String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    authListener?.onSuccess("Login Successful")
                }else{
                    authListener?.onFailed(task.exception.toString())
                }
        }
    }
}