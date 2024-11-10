package com.example.ut1_actividadintegradora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut1_actividadintegradora.databinding.ActivityBuscarBinding

class BuscarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_mainAct)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityBuscarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val buscarActivityButton = binding.buscarActivityButton
        val nombreBusquedaEditText = binding.nombreBusquedaEditText
        val botonVolverBuscarActivity = binding.botonVolverBuscarActivity

        buscarActivityButton.setOnClickListener {
            val nombre: String = nombreBusquedaEditText.text.toString()

            val baseDeDatos = ProductosSQLiteOpenHelper(this@BuscarActivity, ProductosSQLiteOpenHelper.nombreBaseDeDatos, null, ProductosSQLiteOpenHelper.version)
            val producto: Productos? = baseDeDatos.buscarProductoPorNombre(nombre)
            baseDeDatos.cerrarConexion()

            val intent = Intent(this, ListarProductosActivity::class.java)
            intent.putExtra("nombre", producto?.nombre)
            intent.putExtra("marca", producto?.marca)
            intent.putExtra("precio", producto?.precio)
            intent.putExtra("categoria_id", producto?.categoria_id)
            intent.putExtra("disponible", producto?.disponible)

            if (producto == null) {
                Toast.makeText(this, "NO SE HA ENCONTRADO EL PRODUCTO $nombre", Toast.LENGTH_LONG).show()
            }

            startActivity(intent)
        }

        botonVolverBuscarActivity.setOnClickListener {
            finish()
        }
    }
}