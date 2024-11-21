package com.cibertec.minimarketapp.registro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistroViewModel: ViewModel() {
    private lateinit var auth : FirebaseAuth
    val userRegisterFirebase = MutableLiveData<Boolean>()

    fun validaRegister(nombre: String, apellidos: String,
                       correo: String, clave: String){
        //validaciones
        registerFirebase(correo, clave)
    }
    private fun registerFirebase(correo: String, clave: String){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(correo, clave)
            .addOnCompleteListener{
                userRegisterFirebase.value = it.isSuccessful
            }
    }
}