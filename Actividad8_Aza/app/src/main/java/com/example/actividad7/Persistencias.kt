package com.example.actividad7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Persistencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_persistencias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ficheros = getSharedPreferences("ficheros", Context.MODE_PRIVATE)
        val editor = ficheros.edit()

        val fetmail=findViewById<EditText>(R.id.etmail)
        val fetdatos = findViewById<EditText>(R.id.etdatos)
        val grabar = findViewById<Button>(R.id.grabar)
        val leer = findViewById<Button>(R.id.recuperar)
        val volver = findViewById<Button>(R.id.volver)


        grabar.setOnClickListener{
            editor.putString(fetmail.text.toString(), fetdatos.text.toString())
            editor.apply()
            Toast.makeText(this@Persistencias,"Datos guardados", Toast.LENGTH_LONG).show()
            fetmail.text.clear()
            fetdatos.text.clear()
        }
        leer.setOnClickListener{
            val dat = ficheros.getString(fetmail.text.toString(),"")
            if(dat!=null){
                if(dat.length==0){
                    Toast.makeText(this@Persistencias,"No existen datos asociados a ese mail", Toast.LENGTH_LONG).show()
                }
                else{
                    fetdatos.setText(dat)
                }
            }
        }

        volver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}
