package com.example.ut1_actividadintegradora

import java.io.Serializable

// Lo de Serializable lo necesitamos para poder hacer el Intent pasandole el array de Productos
// en ListarProductosActivity.OnCreate
data class Productos (
    val id: Int,
    val nombre: String,
    val marca: String,
    val precio: Double,
    val categoria_id: Int,
    val disponible: Boolean
) : Serializable
