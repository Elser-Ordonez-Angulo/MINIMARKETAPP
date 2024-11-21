package com.cibertec.minimarketapp.detalleoferta

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.R

class DetalleofViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_detalleoferta, parent, false)){

    private var imgDetalled: ImageView? = null
    private var textNombreDetalled: TextView? = null
    private var textNombrePreciod:TextView?=null

    init {
        imgDetalled = itemView.findViewById(R.id.imgProddetalle)
        textNombreDetalled= itemView.findViewById(R.id.txtProdudetalle)
        textNombrePreciod = itemView.findViewById(R.id.txtPreciodetalle)
    }

    fun bind(detalle:Detalleof) {
        textNombreDetalled?.text = detalle.nombre
        textNombrePreciod?. text  = "S/. "+ detalle.precio

        imgDetalled?.let {
            Glide.with(it)
                .load(detalle.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }
}