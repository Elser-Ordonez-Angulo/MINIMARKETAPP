package com.cibertec.minimarketapp.ofertas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.detalleoferta.DetalleofActivity

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class OfertasActivity: AppCompatActivity(), OfertasAdapter.OfertasListener {





    var listOfertas = arrayListOf<Oferta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)



        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Ofertas"




        val recyclerOfertas = findViewById<RecyclerView>(R.id.recyclerOfertas)

        val adapter = OfertasAdapter(this)
        recyclerOfertas.adapter = adapter
        recyclerOfertas.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val db = Firebase.firestore
        db.collection("ofertas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val id = documents["id"] as String
                    val titulo = documents["titulo"] as String
                    val subtitulo = documents["subtitulo"] as String
                    val precio = documents["precio"] as String
                    val regular = documents["regular"] as String
                    val oferte = documents["oferte"] as String
                    val cantidad = documents["cantidad"] as String
                    val imagen = documents["imagen"] as String
                    val valido = documents["valido"] as String


                    val oferta = Oferta(id, titulo, subtitulo, precio, regular, oferte, cantidad, imagen, valido)
                    listOfertas.add(oferta)

                }

                adapter.loadData(listOfertas)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }

    }

    override fun getOfertaSelected(oferta: Oferta) {
        val id = oferta.id
        val intent = Intent(this, DetalleofActivity::class.java)
        intent.putExtra("idOferta", id)
        startActivity(intent)
    }

}