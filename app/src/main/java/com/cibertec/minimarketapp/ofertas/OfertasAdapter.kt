package com.cibertec.minimarketapp.ofertas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OfertasAdapter(val listener: OfertasListener):
    RecyclerView.Adapter<OfertasViewHolder>() {

    var listOfertas = emptyList<Oferta>()

    fun loadData(oferta: List<Oferta>) {
        this.listOfertas = oferta
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfertasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OfertasViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listOfertas.size
    }

    override fun onBindViewHolder(holder: OfertasViewHolder, position: Int) {
        val oferta = listOfertas[position]
        holder.bind(oferta)
        holder.itemView.setOnClickListener {
            listener.getOfertaSelected(oferta)
        }
    }

    interface OfertasListener {
        fun getOfertaSelected(oferta: Oferta)
    }

}

