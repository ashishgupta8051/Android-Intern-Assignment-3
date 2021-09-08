package com.connect.androidinternassignment3.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.connect.androidinternassignment3.databinding.FragmentLoginBinding

import com.connect.androidinternassignment3.ui.activity.MainActivity
import com.connect.androidinternassignment3.utils.AuthListener
import com.connect.androidinternassignment3.utils.MessageUtils
import com.connect.androidinternassignment3.viewmodel.LoginViewModel
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.util.Log
import android.widget.Toast

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.connect.androidinternassignment3.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.android.gms.common.api.ApiException

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import com.facebook.AccessToken

import com.facebook.AccessTokenTracker
import com.facebook.GraphResponse

import org.json.JSONObject

import com.facebook.GraphRequest
import com.facebook.GraphRequest.GraphJSONObjectCallback
import java.lang.Exception


class Login : Fragment(),AuthListener {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private var check = "Hide"
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var mGoogleSignInClient:GoogleSignInClient
    private lateinit var dialog: AlertDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbackManager:CallbackManager


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginviewmodel = loginViewModel
        loginViewModel.authListener = this

        //Password hide/show
        binding.hideLoginPassword.setOnClickListener {
            if(check == "Hide"){
                binding.edtLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                check = "Show"
                binding.hideLoginPassword.setImageResource(R.drawable.ic_outline_lock_24)
            } else{
                binding.edtLoginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                check = "Hide"
                binding.hideLoginPassword.setImageResource(R.drawable.unlock)
            }
        }

        //Show dialog
        if (activity is MainActivity){
            val mainActivity = activity as MainActivity
            dialog = mainActivity.showAlertDialog(requireContext())
        }

        //firebase initialize
        firebaseAuth = MainActivity().getInstanceOfFirebase()

        //Gmail Login
        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)*/
        binding.googleLogin.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                resultLauncher.launch(mGoogleSignInClient.signInIntent)
            }
        }
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                dialog.show()
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    dialog.show()
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    loginWithGoogle(account.idToken)
                } catch (e: ApiException) {
                    dialog.dismiss()
                    MessageUtils().showToastMessage(requireContext(),e.message.toString())
                }
            }
        }

        //Facebook Login
        callbackManager = CallbackManager.Factory.create()
        binding.facebookLogin.fragment = this
        binding.facebookLogin.setPermissions(listOf("email","public_profile"))
        binding.facebookLogin.setOnClickListener {
            dialog.show()
            loginWithFacebook()
        }

        return binding.root
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStarts() {
        MessageUtils().showProgress(binding.loginProgress)
        MessageUtils().hideButton(binding.login)
    }

    override fun onFailed(message: String) {
        MessageUtils().hideProgressBar(binding.loginProgress)
        MessageUtils().showButton(binding.login)
        MessageUtils().showToastMessage(requireContext(),message)
    }

    override fun onSuccess(message: String) {
        MessageUtils().hideProgressBar(binding.loginProgress)
        MessageUtils().showButton(binding.login)
        MessageUtils().showToastMessage(requireContext(),message)
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                dialog.show()
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                dialog.dismiss()
            }

            override fun onError(error: FacebookException) {
                dialog.dismiss()
                MessageUtils().showToastMessage(requireContext(),error.toString())
            }
        })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog.dismiss()
                MessageUtils().showToastMessage(requireContext(),"Login Successful")
            } else {
                dialog.dismiss()
                MessageUtils().showToastMessage(requireContext(),"failed")
            }
        }
    }

    private fun loginWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog.dismiss()
                MessageUtils().showToastMessage(requireContext(),"Login Successful")
            } else {
                MessageUtils().showToastMessage(requireContext(),task.exception.toString())
                dialog.dismiss()
            }
        }
    }
}