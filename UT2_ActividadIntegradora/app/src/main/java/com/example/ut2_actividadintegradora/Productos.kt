package com.example.ut2_actividadintegradora

data class Productos(val id: String, val nombre: String, val marca: String, val precio: Float) {
    override fun toString(): String {
        return "Productos(id = '$id', nombre = '$nombre', marca = '$marca', precio = '$precio'"
    }
}