package com.connect.androidinternassignment3.utils

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class MessageUtils {

    fun showToastMessage(context: Context,mesage:String){
        Toast.makeText(context,mesage,Toast.LENGTH_SHORT).show()
    }

    fun showProgress(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(progressBar: ProgressBar){
        progressBar.visibility = View.GONE
    }

    fun showButton(button: Button){
        button.visibility = View.VISIBLE
    }

    fun hideButton(button: Button){
        button.visibility = View.INVISIBLE
    }
}