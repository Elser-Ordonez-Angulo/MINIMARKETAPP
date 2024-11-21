package com.cibertec.minimarketapp.productos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.carro.CarroActivity
import com.google.firebase.firestore.FirebaseFirestore

class ProductosDetalleActivity: AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_detalle)

        val id = intent.getStringExtra("id")
        val categoria = intent.getStringExtra("categoria")
        val imagen = intent.getStringExtra("imagen")
        val nombre = intent.getStringExtra("nombre")
        val precio = intent.getStringExtra("precio")

        val textNombreProducto = findViewById<TextView>(R.id.textNombreProducto)
        textNombreProducto.text = nombre

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            if (id != null && categoria!= null && imagen!= null
                && nombre!= null && precio!= null) {
                registerFirestore(id, categoria, imagen, nombre, precio, "1")
            }
        }

        val btnContinuar = findViewById<Button>(R.id.btnContinuar)
        btnContinuar.setOnClickListener {
            startActivity(Intent(this, CarroActivity::class.java))
        }

    }

    private fun registerFirestore(id: String, categoria: String, imagen: String,
                                  nombre: String, precio: String, cantidad: String) {

        val carro = hashMapOf(
            "id" to id,
            "categoria" to categoria,
            "imagen" to imagen,
            "nombre" to nombre,
            "precio" to precio,
            "cantidad" to cantidad
        )

        firestore = FirebaseFirestore.getInstance()
        firestore.collection("carro").document()
            .set(carro)
            .addOnSuccessListener {
                // userRegisterFirebase.value = true
            }
            .addOnFailureListener { e ->
                // userRegisterFirebase.value = false
            }
    }

}
