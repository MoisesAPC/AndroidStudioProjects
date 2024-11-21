package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pgl_ut1_examen_moises_antonio_pestano_castro.databinding.ActivityInsertarBinding

class InsertarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializamos binding
        binding = ActivityInsertarBinding.inflate(layoutInflater)
        setContentView((binding.root))

        // Accedemos a los componentes utilizando binding.(id del componente)
        val codigoEditText = binding.codigoEditText
        val nombreEditText = binding.nombreEditText
        val tipoEt = binding.tipoEt
        val numOyentesEt = binding.numOyentesEt
        val Frecuenciaet = binding.Frecuenciaet
        val numPremiosEt = binding.numPremiosEt
        val insertarActBut = binding.insertarActBut
        val insertarActVolver = binding.insertarActVolver

        insertarActBut.setOnClickListener {
            if (!codigoEditText.text.toString().isEmpty() &&
                !nombreEditText.text.toString().isEmpty() &&
                !tipoEt.text.toString().isEmpty() &&
                !numOyentesEt.text.toString().isEmpty() &&
                !Frecuenciaet.text.toString().isEmpty() &&
                !numPremiosEt.text.toString().isEmpty()) {

                val baseDeDatos = RadiosSQLiteOpenHelper(
                    this@InsertarActivity,
                    RadiosSQLiteOpenHelper.nombreBaseDeDatos,
                    null,
                    RadiosSQLiteOpenHelper.version
                )

                val resultado= baseDeDatos.insertarRadio(
                    codigoEditText.text.toString().toInt(),
                    nombreEditText.text.toString(),
                    tipoEt.text.toString(),
                    numOyentesEt.text.toString().toInt(),
                    Frecuenciaet.text.toString().toDouble(),
                    numPremiosEt.text.toString().toInt()
                )

                baseDeDatos.cerrarConexion()
                limpiar(codigoEditText, nombreEditText, tipoEt, numOyentesEt, Frecuenciaet, numPremiosEt)
                if (resultado.toInt() != -1) {
                    Toast.makeText(this, "INSERCIÓN CORRECTA", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "OCURRIÓ UN ERROR EN LA INSERCIÓN DE DATOS", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "ERROR: SE HAN ENCONTRADO STRINGS VACÍAS", Toast.LENGTH_SHORT).show()
            }
        }

        insertarActVolver.setOnClickListener {
            finish()
        }
    }

    private fun limpiar(codigoEditText: EditText, nombreEditText: EditText, tipoEt: EditText, numOyentesEt: EditText, Frecuenciaet: EditText, numPremiosEt: EditText) {
        codigoEditText.text.clear()
        nombreEditText.text.clear()
        tipoEt.text.clear()
        numOyentesEt.text.clear()
        Frecuenciaet.text.clear()
        numPremiosEt.text.clear()
    }
}