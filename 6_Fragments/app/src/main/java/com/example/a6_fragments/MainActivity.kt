package com.example.a6_fragments

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    val planetas = arrayOf("Mercurio", "Venus", "Tierra", "Marte", "Júpiter", "Saturno", "Urano", "Neptuno")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Accedemos a los botones
        val botonfra1 = findViewById<Button>(R.id.botonFragmento01)
        val botonfra2 = findViewById<Button>(R.id.botonFragmento02)

        // Definimos los listener a los botones
        botonfra1.setOnClickListener {
            val fra1 = Fragmento01()
            showFragment(fra1)
        }

        botonfra2.setOnClickListener {
            val fra2 = Fragmento02()
            showFragment(fra2)
        }

        // Accedemos al spinner y definimos el listener
        val espiner = findViewById<Spinner>(R.id.espiner)

        espiner.setOnClickListener {

        }
    }

    fun showFragment(fragmento: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.fragmentoprin, fragmento)
        frag.commit()
    }
}