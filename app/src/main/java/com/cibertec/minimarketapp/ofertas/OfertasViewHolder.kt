package com.cibertec.minimarketapp.ofertas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.R

class OfertasViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_oferta, parent, false)){


    private var textNombreTitulo: TextView? = null
    private var textNombreSubtitulo: TextView? = null
    private var textNombrePrecio: TextView? = null
    private var textNombreRegular: TextView? = null
    private var textNombreProducto: TextView? = null
    private var textNombreCantidad: TextView? = null
    private var imgOferta: ImageView? = null
    private var textNombreValido: TextView? = null



    init {

        textNombreTitulo = itemView.findViewById(R.id.txtTitulo)
        textNombreSubtitulo = itemView.findViewById(R.id.txtSubtitulo)
        textNombrePrecio = itemView.findViewById(R.id.txtPrecio)
        textNombreRegular = itemView.findViewById(R.id.txtPrecioregular)
        textNombreProducto = itemView.findViewById(R.id.txtProducto)
        textNombreCantidad = itemView.findViewById(R.id.txtCantidad)
        imgOferta = itemView.findViewById(R.id.imgProductooferta)
        textNombreValido = itemView.findViewById(R.id.txtValido)






    }

    fun bind(oferta: Oferta) {
        textNombreTitulo?.text = oferta.titulo
        textNombreSubtitulo?.text = oferta.subtitulo
        textNombrePrecio?.text = oferta.precio
        textNombreRegular?.text = oferta.regular
        textNombreProducto?.text = oferta.producto
        textNombreCantidad?.text = oferta.cantidad
        textNombreValido?.text = oferta.valido


        imgOferta?.let {
            Glide.with(it)
                .load(oferta.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }

}