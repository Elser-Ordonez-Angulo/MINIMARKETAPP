package com.cibertec.minimarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.minimarketapp.beneficio.BeneficioActivity
import com.cibertec.minimarketapp.categorias.CategoriasActivity

class BienvenidoActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)

        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Bienvenido"

        val btnButton = findViewById<Button>(R.id.btncontinuar)
        btnButton.setOnClickListener {
            startActivity(Intent(this, BeneficioActivity::class.java))
        }


    }
}