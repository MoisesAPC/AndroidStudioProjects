package com.example.ut2_actividad5

data class Equipos(val id: String, val nombre: String, val titulos: Int) {
    override fun toString(): String {
        return "Equipos(id = '$id', nombre = '$nombre', titulos = '$titulos'"
    }
}