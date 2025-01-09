package com.example.ut3_actividad1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ut3_actividad1.model.Post
import com.example.ut3_actividad1.viewmodel.PostViewModel

// Tenemos como parámetro el viewmodel para poder acceder a los posts obtenedios por Retrofit
@Composable
fun PostListScreen(viewmodel: PostViewModel) {
    val posts = viewmodel.posts

    Column(
        modifier = Modifier
            .padding(8.dp, 32.dp)
    ) {
        Text(
            text = "Post",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(text = post.id.toString())
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = post.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = post.body)
    }
}