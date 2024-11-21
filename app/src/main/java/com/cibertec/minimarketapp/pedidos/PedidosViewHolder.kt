package com.cibertec.minimarketapp.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.minimarketapp.R


class PedidosViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pedidos, parent, false)) {


    private var textCliente: TextView? = null
    private var textDni: TextView? = null
    private var textFecha: TextView? = null
    private var textCiudad: TextView? = null
    private var textDepartamento: TextView? = null
    private var textPedido: TextView? = null
    private var textDireccion: TextView? = null
    private var textTotal: TextView? = null

    init {
        textCliente = itemView.findViewById(R.id.textCliente)
        textDni = itemView.findViewById(R.id.textDni)
        textFecha = itemView.findViewById(R.id.textFecha)
        textCiudad = itemView.findViewById(R.id.textCiudad)
        textDepartamento = itemView.findViewById(R.id.textDepartamento)
        textPedido = itemView.findViewById(R.id.textPedido)
        textDireccion = itemView.findViewById(R.id.textDireccion)
        textTotal = itemView.findViewById(R.id.textTotal)

    }

    fun data(pedidos:Pedidos) {
        textCliente?.text = "Nombre: "+ pedidos.cliente
        textDni?.text = "DNI: "+pedidos.dni
        textCiudad?.text =pedidos.ciudad
        textDepartamento?.text = "- "+ pedidos.departamento
        textPedido?.text ="Pedido: "+ pedidos.pedido
        textDireccion?.text ="Direccion: "+ pedidos.direccion
        textTotal?.text = "Total: S/. "+pedidos.total
        textFecha?.text = "Fecha:  "+ pedidos.fecha

    }
}