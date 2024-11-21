package com.cibertec.minimarketapp.beneficio

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.minimarketapp.R

class BeneficioViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_beneficio, parent, false)){


    private var textNombreTitubeneficio: TextView? = null
    private var textNombreSubtbene: TextView? = null
    private var textNombreEnproductos: TextView? = null
    private var imgBeneficio: ImageView? = null
    private var textNombreRecordatorio: TextView? = null

    init {

        textNombreTitubeneficio = itemView.findViewById(R.id.txtTitubeneficio)
        textNombreSubtbene = itemView.findViewById(R.id.txtSubtbene)
        textNombreEnproductos = itemView.findViewById(R.id.txtEnproductos)
        imgBeneficio = itemView.findViewById(R.id.imgBeneficio)
        textNombreRecordatorio = itemView.findViewById(R.id.txtRecordatorio)



    }

    fun bind(beneficio: Beneficio) {
        textNombreTitubeneficio?.text = beneficio.titulo
        textNombreSubtbene?.text = beneficio.subtitulo
        textNombreEnproductos?.text = beneficio.enproducto
        textNombreRecordatorio?.text = beneficio.recordatorio

        imgBeneficio?.let {
            Glide.with(it)
                .load(beneficio.imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }

}