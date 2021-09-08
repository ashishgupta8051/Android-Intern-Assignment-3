package com.connect.androidinternassignment3.viewmodel

import android.util.Patterns
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.connect.androidinternassignment3.model.UserDetails
import com.connect.androidinternassignment3.repository.LoginRepository
import com.connect.androidinternassignment3.utils.AuthListener

class LoginViewModel : ViewModel(){
    lateinit var cpp:String
    var checkBox = "unchecked"
    var name = ObservableField("")
    var email = ObservableField("")
    var phone = ObservableField("")
    var password = ObservableField("")

    var authListener:AuthListener? = null

    fun onRegisterClick(view:View){
        authListener?.onStarts()
        when {
            name.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your name")
            }
            email.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your email id")
            }
            ! Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches() -> {
                authListener?.onFailed("Please enter valid email id")
            }
            phone.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your phone number")
            }
            password.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your password")
            }
            checkBox == "unchecked" ->{
                authListener?.onFailed("Please checked our term and condition")
            }
            else -> {
                val userDetails = UserDetails(
                    name.get().toString(),
                    email.get().toString(),
                    phone.get().toString(),
                    password.get().toString())
                authListener?.let { LoginRepository(it).register(userDetails) }
            }
        }
    }

    fun onLoginClick(view:View) {
        authListener?.onStarts()
        when {
            email.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your email id")
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches() -> {
                authListener?.onFailed("Please enter valid email id")
            }
            password.get().toString().isNullOrEmpty() -> {
                authListener?.onFailed("Enter your password")
            }
            else -> {
                authListener?.let {
                    LoginRepository(it).login(
                        email.get().toString(),
                        password.get().toString()
                    )
                }
            }
        }
    }
}