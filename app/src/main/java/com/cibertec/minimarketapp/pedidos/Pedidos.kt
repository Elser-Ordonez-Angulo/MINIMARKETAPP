package com.cibertec.minimarketapp.pedidos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pedidos")
data class Pedidos(
    @ColumnInfo(name = "cliente_pedido")
    val cliente: String,
    @ColumnInfo(name = "dni_pedido")
    val dni: String,
    @ColumnInfo(name = "fecha_pedido")
    val fecha: String,

    @ColumnInfo(name = "ciudad_pedido")
    val ciudad: String,
    @ColumnInfo(name = "departameto_pedido")
    val departamento: String,
    @ColumnInfo(name = "pedido_pedido")
    val pedido: String,
    @ColumnInfo(name = "direccion_pedido")
val direccion: String,
    @ColumnInfo(name = "total_pedido")
    val total: String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pedido")
    var idpedido: Int = 0
}
