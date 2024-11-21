package com.cibertec.minimarketapp.pedidos

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.exament2.database.PedidosDataBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PedidosRepository(application: Application) {
    private  val  pedidosDao = PedidosDataBase.getInstance(application).pedidosDao()

    fun  getPedidos(): LiveData<List<Pedidos>> {
        return pedidosDao.list()
    }

    suspend fun insert(pedidos: Pedidos){
        withContext((Dispatchers.IO)){
            pedidosDao.insert(pedidos)
        }
    }
    suspend fun update(pedidos:Pedidos){
        withContext((Dispatchers.IO)){
            pedidosDao.insert(pedidos)
        }
    }
    suspend fun delete(pedidos: Pedidos){
        withContext((Dispatchers.IO)){
            pedidosDao.insert(pedidos)
        }
    }
}