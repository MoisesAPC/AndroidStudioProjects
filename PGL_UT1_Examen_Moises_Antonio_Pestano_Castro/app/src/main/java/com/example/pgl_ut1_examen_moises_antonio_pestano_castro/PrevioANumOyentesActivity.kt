package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityPrevioAnumOyentesBinding

class PrevioANumOyentesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrevioAnumOyentesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_previo_anum_oyentes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityPrevioAnumOyentesBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val volverPrevButton = binding.volverPrevButton
        val verListaButton = binding.verListaButton
        val buscarCodEt = binding.buscarCodEt

        verListaButton.setOnClickListener {
            val numPremios: String = buscarCodEt.text.toString()

            if (numPremios.isEmpty()) {
                Toast.makeText(this, "ERROR: SE HAN ENCONTRADO STRINGS VACÍAS", Toast.LENGTH_SHORT).show()
            }
            else {
                val baseDeDatos = RadiosSQLiteOpenHelper(this@PrevioANumOyentesActivity, RadiosSQLiteOpenHelper.nombreBaseDeDatos, null, RadiosSQLiteOpenHelper.version)
                val listaRadios: List<Radios> = baseDeDatos.buscarRadioPorNumPremios(numPremios.toInt())
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

        volverPrevButton.setOnClickListener {
            finish()
        }
    }
}