package com.cibertec.minimarketapp.categorias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.R

import com.cibertec.minimarketapp.pedidos.PedidosActivity
import com.cibertec.minimarketapp.productos.ProductosActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CategoriasActivity: AppCompatActivity(), CategoriasAdapter.CategoriaListener {

    var listCategorias = arrayListOf<Categoria>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Categorias"

        val recyclerCategorias = findViewById<RecyclerView>(R.id.recyclerCategorias)

        val adapter = CategoriasAdapter(this)
        recyclerCategorias.adapter = adapter
        recyclerCategorias.layoutManager = GridLayoutManager(this, 2)

        val db = Firebase.firestore
        db.collection("categorias")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data

                    val id = documents["id"] as String
                    val nombre = documents["categoria"] as String
                    val imagen = documents["imagen"] as String

                    val categoria = Categoria(id, nombre, imagen)
                    listCategorias.add(categoria)

                }

                adapter.loadData(listCategorias)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }

    }


    //Lista productos de acuerdo al id de su categoria

    override fun getCategoriaSelected(categoria: Categoria) {
        val id = categoria.id
        val intent = Intent(this, ProductosActivity::class.java)
        intent.putExtra("idCategoria", id)
        startActivity(intent)
    }

}