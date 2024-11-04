package com.example.actividad2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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
        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val inicioEditText = findViewById<EditText>(R.id.inicioEditText)
        val finalEditText = findViewById<EditText>(R.id.finalEditText)
        val invertirTextBoton = findViewById<Button>(R.id.invertirTextBoton)
        val extraerSubcadenaBoton = findViewById<Button>(R.id.extraerSubcadenaBoton)
        val textoAlRevesTextView = findViewById<TextView>(R.id.textoAlRevesTextView)
        val subcadenaTextView = findViewById<TextView>(R.id.subcadenaTextView)

        // Capturamos el evento de cuando se pulsa el botón de invertir texto
        invertirTextBoton.setOnClickListener {
            /**
            (inputEditText.text.toString() --> Obtiene el texto de un edit text y lo castea a un String.

             También se puede hacer de la siguiente manera:
            val editText: EditText = findViewById(R.id.miEditText)
            val texto: String = editText.text.toString()

             reversed() invierte el texto
            */
            textoAlRevesTextView.setText((inputEditText.text.toString()).reversed())
        }

        // Capturamos el evento de cuando se pulsa el botón de extraer subcadena
        extraerSubcadenaBoton.setOnClickListener {
            val inputString = inputEditText.text.toString()

            val inicio = inicioEditText.text.toString().toIntOrNull() ?: 0
            val fin =  finalEditText.text.toString().toIntOrNull() ?: 0

            val subString = inputString.substring(inicio, fin)

            subcadenaTextView.setText(subString)
        }
    }
}