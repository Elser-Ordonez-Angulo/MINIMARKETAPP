package com.cibertec.minimarketapp.beneficio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BeneficioAdapter():
    RecyclerView.Adapter<BeneficioViewHolder>() {


    var listBeneficio = emptyList<Beneficio>()

    fun loadData(beneficio: List<Beneficio>) {
        this.listBeneficio = beneficio
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BeneficioViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listBeneficio.size

    }

    override fun onBindViewHolder(holder: BeneficioViewHolder, position: Int) {
        val beneficio = listBeneficio[position]
        holder.bind(beneficio)


        }




}

