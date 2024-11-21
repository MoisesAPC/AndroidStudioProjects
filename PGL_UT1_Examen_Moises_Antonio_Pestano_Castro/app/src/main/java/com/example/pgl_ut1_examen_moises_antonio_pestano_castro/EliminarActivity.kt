package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityEliminarBinding

class EliminarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityEliminarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val eliminaret = binding.eliminaret
        val eliBut = binding.eliBut
        val vovlEli = binding.vovlEli

        eliBut.setOnClickListener {
            val baseDeDatos = RadiosSQLiteOpenHelper(this@EliminarActivity, RadiosSQLiteOpenHelper.nombreBaseDeDatos, null, RadiosSQLiteOpenHelper.version)
            val resultado = baseDeDatos.eliminarRadio(eliminaret.text.toString().toInt())
            baseDeDatos.cerrarConexion()
            eliminaret.text.clear()

            if (resultado != 0) {
                Toast.makeText(this, "ELIMINACIÓN CORRECTA", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "ERROR: NO SE PUDO HACER LA ELIMINACIÓN", Toast.LENGTH_SHORT).show()
            }
        }

        vovlEli.setOnClickListener {
            finish()
        }
    }
}