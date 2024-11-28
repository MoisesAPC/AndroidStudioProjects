package com.example.ut2_actividad3

import androidx.compose.runtime.mutableStateListOf


// Definimos la Data Class donde almacenamos los datos
data class Contacto(val nombre: String, val email: String)

// Definimos la lista de contactos para la lista
val contactos = mutableStateListOf<Contacto>(
    Contacto("Martínez Iñigo", "imarti@gmail.com"),
    Contacto("Hermoso Mario", "mhermo@gmail.com"),
    Contacto("Fernández Nacho", "nfeernandez@hotmail.com"),
    Contacto("Mármol Mika", "mmarmo@outlook.es"),
    Contacto("Bartra Marc", "mbartra@hotmail.es"),
)
