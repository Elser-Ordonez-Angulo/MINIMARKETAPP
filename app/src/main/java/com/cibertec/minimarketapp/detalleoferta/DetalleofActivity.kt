package com.cibertec.minimarketapp.detalleoferta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.pedidos.PedidosActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DetalleofActivity : AppCompatActivity() {

    var listDetalleof = arrayListOf<Detalleof>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalleofertas)


        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Comprar"

        val btnComprar = findViewById<Button>(R.id.btnComprar)
        btnComprar.setOnClickListener {
            startActivity(Intent(this, PedidosActivity::class.java))

        }


        val idOferta = intent.getStringExtra("idOferta")

        val recyclerDetofer = findViewById<RecyclerView>(R.id.recyclerDetofer)

        val adapter = DetalleofAdapter()
        recyclerDetofer.adapter = adapter
        recyclerDetofer.layoutManager = GridLayoutManager(this, 2)

        val db = Firebase.firestore
        db.collection("detalleoferta")
            .whereEqualTo("oferte", idOferta)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val nombre = documents["nombre"] as String
                    val imagen = documents["imagen"] as String
                    val oferte = documents["oferte"] as String
                    val precio = documents["precio"] as String

                    val detalle = Detalleof(oferte,imagen, nombre, precio)
                    listDetalleof.add(detalle)

                }

                adapter.loadData(listDetalleof)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }

    }

}