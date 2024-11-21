package com.cibertec.minimarketapp.pedidos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PedidosDao {
    @Insert
    fun insert(pedidos: Pedidos)

    @Update
    fun update(pedidos: Pedidos)

    @Delete
    fun delete(pedidos: Pedidos)

    @Query("SELECT * FROM pedidos")
    fun list(): LiveData<List<Pedidos>>
}