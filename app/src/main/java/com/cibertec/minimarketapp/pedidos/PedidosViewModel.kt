package com.cibertec.minimarketapp.pedidos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PedidosViewModel (application: Application): AndroidViewModel(application){
    private  val repository = PedidosRepository(application)

    fun getPedidos(): LiveData<List<Pedidos>> {
        return repository.getPedidos()
    }

    fun  insertPedidos(pedidos: Pedidos){
        viewModelScope.launch {
            repository.insert(pedidos)
        }
    }
}