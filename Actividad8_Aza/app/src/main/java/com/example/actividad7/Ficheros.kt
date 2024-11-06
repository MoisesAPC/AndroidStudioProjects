package com.example.actividad7

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Ficheros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ficheros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val fetfecha=findViewById<EditText>(R.id.date)
        val fetdatos = findViewById<EditText>(R.id.dateDato)
        val grabar = findViewById<Button>(R.id.fGrabar)
        val leer = findViewById<Button>(R.id.LEEEER)
        val volver = findViewById<Button>(R.id.back)
        var nombreFIchero=" s"

        grabar.setOnClickListener{
            try {
                nombreFIchero = fetfecha.text.toString().replace("/","-")
                val fichero = OutputStreamWriter(openFileOutput(nombreFIchero, Activity.MODE_PRIVATE))
                fichero.write(fetdatos.text.toString())
                fichero.flush()
                fichero.close()
                Toast.makeText(this@Ficheros, "Fichero guardado", Toast.LENGTH_LONG).show()
            }
            catch (e:IOException){
                Toast.makeText(this@Ficheros, "Error de  Lectura", Toast.LENGTH_LONG).show()
            }
        }
        leer.setOnClickListener{
            if(fileList().contains(nombreFIchero)){
                try{
                    val fich = InputStreamReader(openFileInput(nombreFIchero))
                    val buffer = BufferedReader(fich)
                    var linea = buffer.readLine()
                    val contenidototal = StringBuilder()
                    while(linea != null){
                        contenidototal.append(linea+"\n")
                        linea= buffer.readLine()
                    }
                    buffer.close()
                    fich.close()
                    fetdatos.setText(contenidototal)
                }catch (e: IOException){}
            }
        }

        volver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}
