package com.example.ut3_actividad1.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ut3_actividad1.api.ApiClient
import com.example.ut3_actividad1.model.Post
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    // Propiedades de nuestra clase
    // Definimos los estados de los post a obtener privada para que no se pueda acceder desde fuera
    private val _posts = mutableStateListOf<Post>()
    // Los asociamos a nuestro viewmodel para que se pueda acceder desde fuera
    val posts: List<Post> get() = _posts

    // Inicializamos las propiedades con la función getPost()
    init {
        getPost()
    }

    // Inicializamos _posts obteniendo los post desde Retrofit lo hacemos utilizan try {} catch() {}
    private fun getPost() {
        viewModelScope.launch {
            try {
                _posts.clear()
                _posts.addAll(ApiClient.apiService.getPosts())
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error recuperando Post", e)
            }
        }
    }
}