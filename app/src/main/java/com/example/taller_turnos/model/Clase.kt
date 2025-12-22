package com.example.taller_turnos.model

data class Clase(
    val id: Int,
    var nombre: String,
    var nombreMaestra: String,
    var cupoMaximo: Int,
    var inscriptos: Int,
    var estoyInscrpito: Boolean = false
)
