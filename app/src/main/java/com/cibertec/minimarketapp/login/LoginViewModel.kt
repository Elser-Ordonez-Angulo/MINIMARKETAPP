package com.cibertec.minimarketapp.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    val userLoginError = MutableLiveData<Boolean>()

    //fun validateLogin(email: String, pass: String){
        //if(email.isEmpty() || pass.isEmpty()){
           // userLoginError.value = true
        //}else if  (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //userLoginError.value = true
        //}else if (pass.length < 5){
           // userLoginError.value = true
        //}else {
           // loginFirebase(email, pass)
       // }
    //}

    //private fun loginFirebase(email: String, pass: String){
       // auth = FirebaseAuth.getInstance()
       // auth.signInWithEmailAndPassword(email, pass)
           // .addOnCompleteListener{resut->
                //if (resut.isSuccessful){
                    //userLoginError.value = false
                //}else{
                   // userLoginError.value = true
               // }

            //}


    //}
}