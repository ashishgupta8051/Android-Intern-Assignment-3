package com.connect.androidinternassignment3.ui.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.connect.androidinternassignment3.R
import com.connect.androidinternassignment3.databinding.FragmentSignUpBinding
import com.connect.androidinternassignment3.ui.activity.MainActivity
import com.connect.androidinternassignment3.utils.AuthListener
import com.connect.androidinternassignment3.utils.MessageUtils
import com.connect.androidinternassignment3.viewmodel.LoginViewModel

class SignUp : Fragment(),AuthListener {

    private lateinit var binding:FragmentSignUpBinding
    private lateinit var loginViewModel: LoginViewModel
    private var check = "Hide"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel

        loginViewModel.authListener = this

        //get country code
        loginViewModel.cpp = binding.ccp.selectedCountryCodeWithPlus
        binding.ccp.setOnCountryChangeListener {
            loginViewModel.cpp = binding.ccp.selectedCountryCodeWithPlus
        }

        //CheckBox condition
        binding.checkbox.setOnClickListener {
            if (binding.checkbox.isChecked){
                loginViewModel.checkBox = "checked"
            }else{
                loginViewModel.checkBox = "unchecked"
            }
        }

        //Password hide/show
        binding.hidePassword.setOnClickListener {
            if(check == "Hide"){
                binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                check = "Show"
                binding.hidePassword.setImageResource(R.drawable.ic_outline_lock_24)
            } else{
                binding.edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check = "Hide"
                binding.hidePassword.setImageResource(R.drawable.unlock)
            }
        }

        return binding.root
    }

    override fun onStarts() {
        MessageUtils().showProgress(binding.registerProgress)
        MessageUtils().hideButton(binding.register)
    }

    override fun onFailed(message: String) {
        MessageUtils().hideProgressBar(binding.registerProgress)
        MessageUtils().showButton(binding.register)
        MessageUtils().showToastMessage(requireContext(),message)
    }

    override fun onSuccess(message: String) {
        MessageUtils().hideProgressBar(binding.registerProgress)
        MessageUtils().showButton(binding.register)
        MessageUtils().showToastMessage(requireContext(),message)
    }

}