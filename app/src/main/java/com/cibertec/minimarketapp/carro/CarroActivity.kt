package com.cibertec.minimarketapp.carro

import CarroAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.maps.MapsActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CarroActivity: AppCompatActivity(), CarroAdapter.CarroListener  {

    var lisCarro = arrayListOf<Carro>()

    lateinit var recyclerCategorias: RecyclerView
    lateinit var adapter: CarroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)

        recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCarro)


        adapter = CarroAdapter(this)
        recyclerCategorias.adapter = adapter
        recyclerCategorias.layoutManager = LinearLayoutManager(this)

        loadData()
    }


    private fun loadData() {
        val db = Firebase.firestore
        db.collection("carro")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val idCarro = document.id
                    val id = documents["id"] as String
                    val cantidad = documents["cantidad"] as String
                    val categoria = documents["categoria"] as String
                    val imagen = documents["imagen"] as String
                    val nombre = documents["nombre"] as String
                    val precio = documents["precio"] as String

                    val carro = Carro(idCarro, id, categoria, imagen, nombre, precio, cantidad)
                    lisCarro.add(carro)

                }
                val btnContinuar = findViewById<Button>(R.id.btnIngresarapedido)
                btnContinuar.setOnClickListener {
                    startActivity(Intent(this, MapsActivity::class.java))
                }
                adapter.loadData(lisCarro)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }
    }

    override fun getCarroSelected(carro: Carro) {

    }

    override fun removeCarroSelected(carro: Carro) {

        val db = Firebase.firestore
        Log.v("REMOVE_CARRO", "4")
        Log.v("REMOVE_CARRO", carro.nombre)

        db.collection("carro").document(carro.idCarro)
            .delete()
            .addOnSuccessListener {
                lisCarro.clear()

                loadData()
                Toast.makeText(this, "Producto eliminado correctamente",
                    Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Producto eliminado correctamente",
                    Toast.LENGTH_SHORT).show()
            }



    }


}