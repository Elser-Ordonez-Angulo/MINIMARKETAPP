package com.cibertec.minimarketapp.pedidos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.cibertec.minimarketapp.R


class PedidosDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido)

        val cliente = intent.getStringExtra("cliente_pedido")
        val dni = intent.getStringExtra("dni_pedido")
        val ciudad = intent.getStringExtra("ciudad_pedido")
        val departamento = intent.getStringExtra("departamento_pedido")
        val pedido = intent.getStringExtra("pedido_pedido")
        val direccion = intent.getStringExtra("direccion_pedido")
        val total = intent.getStringExtra("total_pedido")

        val textCliente = findViewById<TextView>(R.id.textCliente)
        val textDni = findViewById<TextView>(R.id.textDni)
        val textCiudad = findViewById<TextView>(R.id.textCiudad)
        val textDepartamento= findViewById<TextView>(R.id.textDepartamento)
        val textPedido = findViewById<TextView>(R.id.textPedido)
        val textDireccion = findViewById<TextView>(R.id.textDireccion)
        val textTotal = findViewById<TextView>(R.id.textTotal)


        textCliente.text = cliente
        textDni.text = dni
        textCiudad.text = ciudad
        textDepartamento.text = departamento
        textPedido.text = pedido
        textDireccion.text = direccion
        textTotal.text = total
    }

}