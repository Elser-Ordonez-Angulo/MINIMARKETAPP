package com.cibertec.minimarketapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cibertec.minimarketapp.BienvenidoActivity
import com.cibertec.minimarketapp.MinimarketApp
import com.cibertec.minimarketapp.MinimarketPreferences
import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.RecuperarActivity
import com.cibertec.minimarketapp.registro.RegistroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("NAME_SHADOWING")
class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtClave = findViewById<EditText>(R.id.edtClave)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        btnIngresar.setOnClickListener {
            val email = edtCorreo.text.toString()
            val pass = edtClave.text.toString()
            loginAuth(email, pass)
        }


        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))

        }


        val btnClave = findViewById<Button>(R.id.btnClave)
        btnClave.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))

        }

    }
        private fun loginAuth(email: String, pass: String) {
            auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userId = it.result.user?.uid
                        userId?.let {
                            searchFirestore(userId)
                        }
                    }
                }
        }

        private fun searchFirestore(uid: String) {
            firestore = FirebaseFirestore.getInstance()
            firestore.collection("usuarios").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    val data = document.data

                    val name = data?.get("name") as String
                    val lastname = data?.get("lastname") as String
                    val email = data?.get("email") as String

                    MinimarketApp.prefs?.setString("email", email)
                    MinimarketApp.prefs?.setString("name", name)
                    MinimarketApp.prefs?.setString("lastname", lastname)


                    //Star que inicializa a la pantalla Bienvenido Activity

                    startActivity(Intent(this, BienvenidoActivity::class.java))

                }
                .addOnFailureListener { exception ->
                    // Log.w(TAG, "Error getting documents: ", exception)
                }
        }




    }




