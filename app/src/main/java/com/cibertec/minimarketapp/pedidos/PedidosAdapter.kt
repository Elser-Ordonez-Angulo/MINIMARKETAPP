package com.cibertec.minimarketapp.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PedidosAdapter(//val list: List<Nota>,
    val listener: ItemClickListener):
    RecyclerView.Adapter<PedidosViewHolder>() {


    private var list = emptyList<Pedidos>()

    fun setPedidos(pedidos: List<Pedidos>){
        this.list = pedidos
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PedidosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedidos = list[position]
        holder.data(pedidos)
        holder.itemView.setOnClickListener {
            listener.onClickListener(pedidos)
        }
    }

    interface ItemClickListener {
        fun onClickListener(pedidos: Pedidos)
    }
}