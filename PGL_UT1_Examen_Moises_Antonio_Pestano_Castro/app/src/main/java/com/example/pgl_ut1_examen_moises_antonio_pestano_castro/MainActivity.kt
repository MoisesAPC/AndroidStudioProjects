package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_mainAct)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos la variable creándolo y vinculándolo
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Mostramos la vista raíz
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val cerrarButton = binding.cerrarButton
        val insertarButton = binding.insertarButton
        val buttonMostrarNumOyentes = binding.buttonMostrarNumOyentes
        val eliminarButton = binding.eliminarButton
        val listarButton = binding.listarButton
        val modificarButton = binding.modificarButton

        insertarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, InsertarActivity::class.java)
            startActivity(intent)
        }

        eliminarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EliminarActivity::class.java)
            startActivity(intent)
        }

        buttonMostrarNumOyentes.setOnClickListener {
            val intent = Intent(this@MainActivity, PrevioANumOyentesActivity::class.java)
            startActivity(intent)
        }

        listarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, BuscarPorCodigoActivity::class.java)
            startActivity(intent)
        }

        modificarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ModificarActivity::class.java)
            startActivity(intent)
        }

        cerrarButton.setOnClickListener {
            finish()
        }
    }
}