package com.example.ut1_actividadintegradora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ut1_actividadintegradora.databinding.ActivityListarProductosBinding

class ListarProductosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_mainAct)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityListarProductosBinding.inflate(layoutInflater)
        setContentView((binding.root))

        val recyclerView = binding.recyclerView

        // Recibimos la lista de productos del MainActivity (de la funcion "listarProductosButton.setOnClickListener")
        // Usando "getSerializableExtra" podemos recibir un array de Productos desde un Intent
        val listaProductos = intent.getSerializableExtra("listaProductos") as? List<Productos>

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Si recibimos una lista de productos, usamos esa lista
        // (de esta manera hacemos que el botón de Listar del menú principal funcione)
        if (listaProductos != null && listaProductos.isNotEmpty()) {
            val adaptadorListaProductos = AdaptadorProductos(listaProductos) {}
            recyclerView.adapter = adaptadorListaProductos
        }
        // En caso contrario, asumimos que vamos a listar solamente un solo producto
        else {
            // Recibimos los datos del Intent de BuscarActivity (los del a función "buscarActivityButton.setOnClickListener")
            val id = intent.getIntExtra("id", 0)
            val nombre = intent.getStringExtra("nombre") ?: ""
            val marca = intent.getStringExtra("marca") ?: ""
            val precio = intent.getDoubleExtra("precio", 0.0)
            val categoriaId = intent.getIntExtra("categoria_id", 0)
            val disponible = intent.getBooleanExtra("disponible", false)

            // Creamos un objeto Productos con los datos recibidos y lo ponemos en una lista con el listof
            val productoObjeto = Productos(id, nombre, marca, precio, categoriaId, disponible)
            val producto = listOf(productoObjeto)

            val adaptadorProducto = AdaptadorProductos(producto) {}
            recyclerView.adapter = adaptadorProducto
        }

        val volverActListarProd = binding.volverActListarProd

        volverActListarProd.setOnClickListener {
            finish()
        }
    }
}