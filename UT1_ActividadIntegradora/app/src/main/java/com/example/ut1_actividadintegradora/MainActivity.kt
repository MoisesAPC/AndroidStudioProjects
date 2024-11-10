package com.example.ut1_actividadintegradora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut1_actividadintegradora.databinding.ActivityMainBinding

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
        val insertarButton = binding.insertarButton
        val listarButton = binding.listarButton
        val buscarButton = binding.buscarButton
        val cerrarButton = binding.cerrarButton
        val modificarButton = binding.modificarButton
        val eliminarButton = binding.eliminarButton
        val eliminarTodosButton = binding.eliminarTodosButton

        insertarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, InsertarActivity::class.java)
            startActivity(intent)
        }

        buscarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, BuscarActivity::class.java)
            startActivity(intent)
        }

        modificarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ModificarActivity::class.java)
            startActivity(intent)
        }

        eliminarButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EliminarActivity::class.java)
            startActivity(intent)
        }

        eliminarTodosButton.setOnClickListener {
            val baseDeDatos = ProductosSQLiteOpenHelper(this@MainActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            val resultado = baseDeDatos.eliminarTodosLosProductos()
            baseDeDatos.cerrarConexion()

            if (resultado != 0) {
                Toast.makeText(this, "ELIMINACIÓN CORRECTA", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "ERROR: NO SE PUDO HACER LA ELIMINACIÓN", Toast.LENGTH_SHORT).show()
            }
        }

        cerrarButton.setOnClickListener {
            finish()
        }

        listarButton.setOnClickListener {
            val baseDeDatos = ProductosSQLiteOpenHelper(this@MainActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            val listaProductos: List<Productos> = baseDeDatos.buscarProductos()
            baseDeDatos.cerrarConexion()

            val intent = Intent(this, ListarProductosActivity::class.java)
            if (listaProductos.isEmpty()) {
                Toast.makeText(this, "NO HAY PRODUCTOS", Toast.LENGTH_LONG).show()
            }
            else {
                //pasamos la lista de productos como ArrayList
                intent.putExtra("listaProductos", ArrayList(listaProductos))
                startActivity(intent)
            }
        }
    }
}