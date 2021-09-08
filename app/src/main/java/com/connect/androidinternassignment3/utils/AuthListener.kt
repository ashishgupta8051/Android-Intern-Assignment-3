package com.connect.androidinternassignment3.utils

interface AuthListener {

    fun onStarts()
    fun onFailed(message:String)
    fun onSuccess(message: String)
}