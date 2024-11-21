package com.cibertec.minimarketapp.productos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProductosActivity : AppCompatActivity(), ProductosAdapter.ProductoListener {

    var lisProducto = arrayListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas)

        val idCategoria = intent.getStringExtra("idCategoria")

        val recyclerProductos = findViewById<RecyclerView>(R.id.recyclerProductos)

        val adapter = ProductosAdapter(this)
        recyclerProductos.adapter = adapter
        recyclerProductos.layoutManager = GridLayoutManager(this, 2)

        val db = Firebase.firestore
        db.collection("productos")
            .whereEqualTo("categoria", idCategoria)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documents = document.data
                    val id = document.id
                    val nombre = documents["nombre"] as String
                    val imagen = documents["imagen"] as String
                    val categoria = documents["categoria"] as String
                    val precio = documents["precio"] as String

                    val producto = Producto(id,categoria,imagen, nombre, precio)
                    lisProducto.add(producto)

                }

                adapter.loadData(lisProducto)
            }
            .addOnFailureListener { exception ->
                Log.w("CIBERTEC", "Error getting documents.", exception)
            }

    }

    override fun getProductoSelected(producto: Producto) {
        val intent = Intent(this, ProductosDetalleActivity::class.java)
        intent.putExtra("id",producto.id)
        intent.putExtra("categoria", producto.categoria)
        intent.putExtra("imagen", producto.imagen)
        intent.putExtra("nombre", producto.nombre)
        intent.putExtra("precio", producto.precio)
        startActivity(intent)
    }

}