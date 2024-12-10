package com.example.ut2_actividadintegradora

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
import com.example.ut2_actividadintegradora.ui.theme.UT2_ActividadIntegradoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT2_ActividadIntegradoraTheme {
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
fun AppMainPreview() {
    UT2_ActividadIntegradoraTheme {
        AppMain()
    }
}