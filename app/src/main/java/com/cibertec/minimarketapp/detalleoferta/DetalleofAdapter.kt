package com.cibertec.minimarketapp.detalleoferta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetalleofAdapter():
    RecyclerView.Adapter<DetalleofViewHolder>() {

    var listDetalleof = emptyList<Detalleof>()

    fun loadData(detalle: List<Detalleof>) {
        this.listDetalleof = detalle
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleofViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetalleofViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listDetalleof.size
    }

    override fun onBindViewHolder(holder: DetalleofViewHolder, position: Int) {
        val detalle = listDetalleof[position]
        holder.bind(detalle)
    }
}