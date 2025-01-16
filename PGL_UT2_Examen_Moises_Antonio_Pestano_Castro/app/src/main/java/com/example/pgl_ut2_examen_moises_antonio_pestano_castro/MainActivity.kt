package com.example.pgl_ut2_examen_moises_antonio_pestano_castro

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
import com.example.pgl_ut2_examen_moises_antonio_pestano_castro.ui.theme.PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppMain()
                }
            }
        }
    }
}

@Composable
fun AppMain() {

}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    PGL_UT2_Examen_Moises_Antonio_Pestano_CastroTheme {
        AppMain();
    }
}