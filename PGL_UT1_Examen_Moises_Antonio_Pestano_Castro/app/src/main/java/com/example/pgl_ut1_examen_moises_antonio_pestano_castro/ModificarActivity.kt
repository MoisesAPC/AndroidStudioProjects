package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityEliminarBinding
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityModificarBinding

class ModificarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityModificarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val cerrarbut = binding.cerrarbut
        val numOyentesMinEdit = binding.numOyentesMinEdit
        val numpremiosEditNUEVO = binding.numpremiosEditNUEVO
        val actualizarbut = binding.actualizarbut

        // numOyentesMinEdit es el valor de oyentes mínimo para que se pueda hacer la actualización.
        actualizarbut.setOnClickListener {
            val baseDeDatos = RadiosSQLiteOpenHelper(this@ModificarActivity, RadiosSQLiteOpenHelper.nombreBaseDeDatos, null, RadiosSQLiteOpenHelper.version)
            val resultado = baseDeDatos.actualizarNumPremios(numOyentesMinEdit.toString().toInt(), numpremiosEditNUEVO.toString().toInt())
            baseDeDatos.cerrarConexion()
            numOyentesMinEdit.text.clear()
            numpremiosEditNUEVO.text.clear()

            if (resultado != 0) {
                Toast.makeText(this, "ACTUALIZACIÓN CORRECTA", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "ERROR: NO SE PUDO HACER LA ACTUALIZACIÓN", Toast.LENGTH_SHORT).show()
            }
        }

        cerrarbut.setOnClickListener {
            finish()
        }
    }
}