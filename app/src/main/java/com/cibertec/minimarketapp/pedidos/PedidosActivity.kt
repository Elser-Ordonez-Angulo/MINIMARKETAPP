package com.cibertec.minimarketapp.pedidos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import java.time.LocalDateTime
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibertec.minimarketapp.R

import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.format.DateTimeFormatter

class PedidosActivity: AppCompatActivity(), PedidosAdapter.ItemClickListener {
    private lateinit var viewModel: PedidosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
          //cabecera
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Pedidos"

        viewModel = ViewModelProvider(this)[PedidosViewModel::class.java]

        val recyclerPedidos =
            findViewById<RecyclerView>(R.id.recyclerPedidos)

        val floatingRegistrar =
            findViewById<FloatingActionButton>(R.id.floatingRegistrar)
        floatingRegistrar.setOnClickListener{
            registerAndUpdateNote()
        }

        val adapter = PedidosAdapter(this)
        recyclerPedidos.adapter = adapter
        recyclerPedidos.layoutManager =LinearLayoutManager(this)
        recyclerPedidos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )


        viewModel.getPedidos().observe(this){pedidos ->
            if (pedidos.isNotEmpty()){
                pedidos?.let {
                    adapter.setPedidos(pedidos)
                }
            }

        }

    }



    override fun onClickListener(pedidos:Pedidos) {
        val intent = Intent(this,
            PedidosDao::class.java)
        intent.putExtra("cliente",pedidos.cliente)
        intent.putExtra("dni", pedidos.dni)
        intent.putExtra("ciudad",pedidos.ciudad)
        intent.putExtra("departamento", pedidos.departamento)
        intent.putExtra("pedido", pedidos.pedido)
        intent.putExtra("direccion", pedidos.direccion)
        intent.putExtra("total", pedidos.total)
        startActivity(intent)
    }

    private fun registerAndUpdateNote() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_pedido, null)

        val titleAlertPedidos = "Registrar pedidos"

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(titleAlertPedidos)

        val mAlertDialog = mBuilder.show()

        val buttonCreate  = mDialogView.findViewById<Button>(R.id.btnCreate)

        val editTextClienteCreate  = mDialogView.findViewById<EditText>(R.id.edtCliente)
        val editTextDniCreate  = mDialogView.findViewById<EditText>(R.id.edtDni)
        val editTextCiudadCreate  = mDialogView.findViewById<EditText>(R.id.edtCiudad)
        val editTextDepartamentoCreate  = mDialogView.findViewById<EditText>(R.id.edtDepartamento)
        val editTextPedidoCreate  = mDialogView.findViewById<EditText>(R.id.edtPedido)
        val editTextDireccionCreate  = mDialogView.findViewById<EditText>(R.id.edtDireccion)
        val editTextTotalCreate  = mDialogView.findViewById<EditText>(R.id.edtTotal)

        buttonCreate.setOnClickListener {

            mAlertDialog.dismiss()

            val editTextClienteCreate = editTextClienteCreate.text.toString()
            val editTextDniCreate = editTextDniCreate.text.toString()
            val editTextCiudadCreate = editTextCiudadCreate.text.toString()
            val editTextDepartamentoCreate = editTextDepartamentoCreate.text.toString()
            val editTextPedidoCreate =editTextPedidoCreate.text.toString()
            val editTextDireccionCreate =editTextDireccionCreate.text.toString()
            val editTextTotalCreate =editTextTotalCreate.text.toString()


            val currentDateTime = LocalDateTime.now().formatChangeNote()

            val  pedidos= Pedidos( editTextClienteCreate, editTextDniCreate,currentDateTime, editTextCiudadCreate,editTextDepartamentoCreate,editTextPedidoCreate,editTextDireccionCreate,editTextTotalCreate)
            viewModel.insertPedidos(pedidos)
        }

    }

    fun LocalDateTime.formatChangeNote(): String
            = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
}

