package com.connect.androidinternassignment3.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.connect.androidinternassignment3.R
import com.connect.androidinternassignment3.databinding.ActivityMainBinding
import com.connect.androidinternassignment3.ui.fragment.Home
import com.connect.androidinternassignment3.ui.fragment.Login
import com.connect.androidinternassignment3.ui.fragment.SignUp
import com.connect.androidinternassignment3.utils.TabAdapter
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var tabAdapter: TabAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Change Status bar color
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.red)

        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(Login(), "Login")
        tabAdapter.addFragment(SignUp(), "Sign Up")
        binding.viewPager.adapter = tabAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    fun showAlertDialog(requireContext: Context) : AlertDialog{
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext)
        val view = layoutInflater.inflate(R.layout.progressdialog,null)
        builder.setCancelable(false).setView(view)
        val progressDialog = builder.create()
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.window!!.attributes.windowAnimations = android.R.style.Animation_Toast

        return progressDialog
    }

    fun getInstanceOfFirebase(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit()
    }

    fun loadFragmentWithBack(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).addToBackStack("back").commit();
    }

}