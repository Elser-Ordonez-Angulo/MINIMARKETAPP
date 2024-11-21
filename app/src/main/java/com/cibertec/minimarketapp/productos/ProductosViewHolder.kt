package com.cibertec.minimarketapp.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.R

class ProductosViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_fruta, parent, false)){

    private var imgProducto: ImageView? = null
    private var textNombreProducto: TextView? = null
    private var textNombrePrecio:TextView?=null

    init {
        imgProducto = itemView.findViewById(R.id.imgFruta)
        textNombreProducto = itemView.findViewById(R.id.textFruta)
        textNombrePrecio = itemView.findViewById(R.id.textPrecio)
    }

    fun bind(producto: Producto) {
        textNombreProducto?.text = producto.nombre
        textNombrePrecio?. text  = "S/. "+ producto.precio

        imgProducto?.let {
            Glide.with(it)
                .load(producto.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }
}