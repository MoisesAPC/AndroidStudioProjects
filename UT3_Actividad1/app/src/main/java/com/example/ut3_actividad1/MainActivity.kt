package com.example.ut3_actividad1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ut3_actividad1.ui.theme.UT3_Actividad1Theme
import com.example.ut3_actividad1.view.PostListScreen
import com.example.ut3_actividad1.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT3_Actividad1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Esta aplicación mostrará los contenidos de https://jsonplaceholder.typicode.com/posts
                    // en una lista
                    val viewModel = PostViewModel()
                    PostListScreen(viewModel)
                }
            }
        }
    }
}
