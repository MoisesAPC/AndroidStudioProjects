package com.example.pgl_ut2_examen_moises_antonio_pestano_castro

data class Fincas(val Codigofinca: String, val Nombre: String, val Direccion: String, val Localidad: String, val Superficie: Double) {
    override fun toString(): String {
        return "Fincas(Codigofinca='$Codigofinca', Nombre='$Nombre', Direccion='$Direccion', Localidad='$Localidad', Superficie=$Superficie)"
    }
}
