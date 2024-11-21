package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityBuscarPorCodigoBinding
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityPrevioAnumOyentesBinding

class BuscarPorCodigoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarPorCodigoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_por_codigo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityBuscarPorCodigoBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val buscarPorCodVer = binding.buscarPorCodVer
        val buscarPorCodVolver = binding.buscarPorCodVolver
        val buscarPorCodigo = binding.buscarPorCodigo

        buscarPorCodVer.setOnClickListener {
            val numPremios: String = buscarPorCodigo.text.toString()

            if (numPremios.isEmpty()) {
                Toast.makeText(this, "ERROR: SE HAN ENCONTRADO STRINGS VACÍAS", Toast.LENGTH_SHORT).show()
            }
            else {
                val baseDeDatos = RadiosSQLiteOpenHelper(this@BuscarPorCodigoActivity, RadiosSQLiteOpenHelper.nombreBaseDeDatos, null, RadiosSQLiteOpenHelper.version)
                val listaRadios: List<Radios> = baseDeDatos.buscarRadioPorCodigo(numPremios.toInt())
                baseDeDatos.cerrarConexion()

                val intent = Intent(this, NumOyentesListarActivity::class.java)
                if (listaRadios.isEmpty()) {
                    Toast.makeText(this, "NO SE HAN ENCONTRADO RADIOS con $numPremios.toInt() premios", Toast.LENGTH_LONG).show()
                }
                else {
                    //pasamos la lista de radios como ArrayList
                    intent.putExtra("listaRadios", ArrayList(listaRadios))
                    startActivity(intent)
                }
            }
        }

        buscarPorCodVolver.setOnClickListener {
            finish()
        }
    }
}