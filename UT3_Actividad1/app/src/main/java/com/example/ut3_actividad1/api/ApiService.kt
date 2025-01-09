package com.example.ut3_actividad1.api

import com.example.ut3_actividad1.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    // El "suspend" es para que se puedan utilizar corrutinas
    suspend fun getPosts(): List<Post>
}