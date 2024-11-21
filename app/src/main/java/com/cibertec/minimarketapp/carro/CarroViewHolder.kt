package com.cibertec.minimarketapp.carro

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import com.cibertec.minimarketapp.R
import com.cibertec.minimarketapp.carro.Carro

class CarroViewHolder (inflater: LayoutInflater, parent: ViewGroup,
                       listener:  CategoriaHolderListener):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_carro, parent, false)){

    private var imgProducto: ImageView? = null
    private var textNombreProducto: TextView? = null
    private var imgEliminar: ImageView? = null

    init {
        imgProducto = itemView.findViewById(R.id.imgProducto)
        textNombreProducto = itemView.findViewById(R.id.textNombreProducto)
        imgEliminar = itemView.findViewById(R.id.imgEliminar)
        imgEliminar?.setOnClickListener {
            Log.v("REMOVE_CARRO", "1")
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                Log.v("REMOVE_CARRO", "2")
                listener.getCategoriaRemoveHolderSelected(position)
            }
        }
    }

    fun bind(carro: Carro) {
        textNombreProducto?.text = carro.nombre

        imgProducto?.let {
            Glide.with(it)
                .load(carro.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }

    interface CategoriaHolderListener {
        fun getCategoriaRemoveHolderSelected(position: Int)
    }

}