package com.cibertec.minimarketapp.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriasAdapter(val listener: CategoriaListener):
    RecyclerView.Adapter<CategoriasViewHolder>() {

    var listCategorias = emptyList<Categoria>()

    fun loadData(categorias: List<Categoria>) {
        this.listCategorias = categorias
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriasViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listCategorias.size
    }

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        val categoria = listCategorias[position]
        holder.bind(categoria)
        holder.itemView.setOnClickListener {
            listener.getCategoriaSelected(categoria)
        }
    }

    interface CategoriaListener {
        fun getCategoriaSelected(categoria: Categoria)
    }

}

