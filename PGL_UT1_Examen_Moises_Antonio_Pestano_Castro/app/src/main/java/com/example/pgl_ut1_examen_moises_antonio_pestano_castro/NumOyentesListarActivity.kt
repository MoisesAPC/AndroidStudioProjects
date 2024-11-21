package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityNumOyentesListarBinding
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityPrevioAnumOyentesBinding

class NumOyentesListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNumOyentesListarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_num_oyentes_listar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityNumOyentesListarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        val recyclerView = binding.recyclerView

        // Recibimos la lista de productos del MainActivity (de la funcion "listarRadiosButton.setOnClickListener")
        // Usando "getSerializableExtra" podemos recibir un array de Productos desde un Intent
        val listaRadios = intent.getSerializableExtra("listaRadios") as? List<Radios>

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adaptadorListaRadios = AdaptadorRadios(listaRadios) {}
        recyclerView.adapter = adaptadorListaRadios

        val butvolv = binding.butvolv

        butvolv.setOnClickListener {
            finish()
        }
    }
}