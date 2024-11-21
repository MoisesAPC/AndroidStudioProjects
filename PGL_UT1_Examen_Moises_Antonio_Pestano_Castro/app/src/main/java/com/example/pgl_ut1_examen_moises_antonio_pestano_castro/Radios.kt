package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import java.io.Serializable

data class Radios (
    val codigo: Int,
    val nombre: String,
    val tipo: String,
    val numoyentes: Int,
    val frecuencia: Double,
    val numpremios: Int
) : Serializable
