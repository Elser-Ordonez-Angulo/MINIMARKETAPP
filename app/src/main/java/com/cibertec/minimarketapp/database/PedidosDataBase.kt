package com.example.exament2.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.Room
import com.cibertec.minimarketapp.pedidos.Pedidos
import com.cibertec.minimarketapp.pedidos.PedidosDao


@Database(entities = [Pedidos::class], version =1)
abstract class PedidosDataBase : RoomDatabase(){
    abstract fun  pedidosDao():PedidosDao

    companion object {//static --->estatico
    private  const val DATABASE_NAME ="cibertec_database"

        @Volatile
        private var  INSTANCE : PedidosDataBase? = null

        fun getInstance(context: Context): PedidosDataBase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }
        private fun buildDatabase(context: Context): PedidosDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                PedidosDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}