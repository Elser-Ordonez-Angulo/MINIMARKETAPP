package com.cibertec.minimarketapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RecuperarActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperarclave)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Recuperar Contraseña"

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnSendEmail = findViewById<Button>(R.id.btnSendEmail)
        btnSendEmail.setOnClickListener {
            val email = edtEmail.text.toString()
            recoveryPassword(email)
        }
    }

    private fun recoveryPassword(email: String) {
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Verifique su correo", Toast.LENGTH_SHORT).show()
                }
            }
    }
}