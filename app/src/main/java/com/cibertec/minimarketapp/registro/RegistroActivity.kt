package com.cibertec.minimarketapp.registro

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.minimarketapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity: AppCompatActivity() {

    private lateinit var viewModel: RegistroViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivity_registro)

        viewModel=ViewModelProvider(this)[RegistroViewModel::class.java]




        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Regístrate"


        val edtNombre=findViewById<EditText>(R.id.edtNombre)
        val edtApellidos=findViewById<EditText>(R.id.edtApellidos)
        val edtCorreo=findViewById<EditText>(R.id.edtCorreo)
        val edtClave=findViewById<EditText>(R.id.edtClave)
        val edtRepClave=findViewById<EditText>(R.id.edtRepClave)
        val btnRegister=findViewById<Button>(R.id.btnHola)


        btnRegister.setOnClickListener{
            val nombre=edtNombre.text.toString()
            val apellidos=edtApellidos.text.toString()
            val correo=edtCorreo.text.toString()
            val clave=edtClave.text.toString()
            val rptclave=edtRepClave.text.toString()

            registerAuth(nombre, apellidos, correo, clave)

        }

        observerFirebase()


    }

    private fun observerFirebase(){
        viewModel.userRegisterFirebase.observe(this){
            if (it){
                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Registro incorrecto", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun registerAuth(name: String, lastname: String, email: String,
                             password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = it.result.user?.uid
                    userId?.let {
                        registerFirestore(userId, name, lastname, email)
                    }
                }else {

                }
            }
    }

    private fun registerFirestore(uid: String, name: String, lastname: String, email: String) {
        firestore = FirebaseFirestore.getInstance()
        val user = hashMapOf(
            "name" to name,
            "lastname" to lastname,
            "email" to email,
        )
        firestore.collection("usuarios").document(uid)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
    }

}

