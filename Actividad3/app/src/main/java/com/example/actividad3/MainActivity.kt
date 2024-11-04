package com.example.actividad3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    fun goToLogueadoActivity(usuario: String) {
        // Creamos el objeto Intent a través del cuál vamos a enviar parámetros a la otra Activity
        val intento1 = Intent(this@MainActivity, LogueadoCorrectamenteActivity::class.java)
        intento1.putExtra("usuario", usuario)
        startActivity(intento1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Accedemos a los componentes
        val usuarioEditText = findViewById<EditText>(R.id.usuarioEditText)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonEntrar = findViewById<Button>(R.id.buttonEntrar)

        // Capturamos el evento de cuando se pulsa el botón de Entrar
        buttonEntrar.setOnClickListener {
            val usuario = "Admin"
            val password = "1906Red"

            val usuarioIntroducido = usuarioEditText.text.toString()
            val passwordIntroducido = editTextPassword.text.toString()

            if ((usuario == usuarioIntroducido) && (password == passwordIntroducido)) {
                goToLogueadoActivity(usuarioIntroducido)
            }
            else {
                Toast.makeText(this, "Usuario y/o contraseñas incorrectos", Toast.LENGTH_LONG).show();
            }
        }
    }
}