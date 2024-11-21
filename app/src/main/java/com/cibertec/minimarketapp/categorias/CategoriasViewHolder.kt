package com.cibertec.minimarketapp.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.R

class CategoriasViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_categoria, parent, false)){

    private var imgCategoria: ImageView? = null
    private var textNombreCategoria: TextView? = null
//inicializamos las variables
    init {
        imgCategoria = itemView.findViewById(R.id.imgCategoria)
        textNombreCategoria = itemView.findViewById(R.id.txtNombreCategoria)


    }

    fun bind(categoria: Categoria) {
        textNombreCategoria?.text = categoria.nombre

        imgCategoria?.let {
            Glide.with(it)
                .load(categoria.imagen)
                //condicionales de imagen
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }

}