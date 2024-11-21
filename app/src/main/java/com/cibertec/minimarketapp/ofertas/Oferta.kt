package com.cibertec.minimarketapp.ofertas

data class Oferta (
    val id: String,
    val titulo: String,
    val subtitulo: String,
    val precio: String,
    val regular: String,
    val producto: String,
    val cantidad: String,
    val imagen: String,
    val valido: String,
)
