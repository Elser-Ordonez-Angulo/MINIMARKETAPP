package com.cibertec.minimarketapp.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(val listener:ProductosAdapter.ProductoListener):
    RecyclerView.Adapter<ProductosViewHolder>() {

    var listProducto = emptyList<Producto>()

    fun loadData(productos: List<Producto>) {
        this.listProducto = productos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listProducto.size
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val producto = listProducto[position]
        holder.bind(producto)
        holder.itemView.setOnClickListener {
            listener.getProductoSelected(producto)
        }
    }

    interface ProductoListener {
        fun getProductoSelected(producto: Producto)
    }
}