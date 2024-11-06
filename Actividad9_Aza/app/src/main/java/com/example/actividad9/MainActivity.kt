package com.example.actividad9

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val codigo =  findViewById<EditText>(R.id.codigo)
        val nombre =  findViewById<EditText>(R.id.nombre)
        val edad =  findViewById<EditText>(R.id.edad)
        val insertar = findViewById<Button>(R.id.insertar)
        val buscar = findViewById<Button>(R.id.buscar)
        val modificar = findViewById<Button>(R.id.modificar)
        val eliminar = findViewById<Button>(R.id.eliminar)
        val mtodos = findViewById<Button>(R.id.mtodos)
        val espin = findViewById<Spinner>(R.id.spinner)
        fun limpiar(){
            codigo.text.clear()
            nombre.text.clear()
            edad.text.clear()
        }

        insertar.setOnClickListener{
            val autor = AutoresSQLiteOpenHelper(this@MainActivity,"Autores",null,1)
            val bd = autor.writableDatabase
            val registro = ContentValues()
            registro.put("codigo", codigo.text.toString().toInt())
            registro.put("nombre", nombre.text.toString())
            registro.put("edad",edad.text.toString().toInt())
            bd.insert("Autores",null,registro)
            bd.close()
            muestraMensaje("Insertado correctamente")
            limpiar()
        }
        buscar.setOnClickListener{
            val autor = AutoresSQLiteOpenHelper(this@MainActivity,"Autores",null,1)
            val bd = autor.writableDatabase
            val regis = bd.rawQuery("Select nombre, edad FROM Autores where CODIGO = ${codigo.text.toString().toInt()}",null)
            if(regis.moveToFirst()){
                nombre.setText(regis.getString(0))
                edad.setText(regis.getInt(1).toString())

            }else{
                muestraMensaje("El codigo del autor no se encuentra en la base de datos")

            }
            bd.close()

        }
        modificar.setOnClickListener{
            if (!codigo.text.toString().isEmpty()){
                val autor = AutoresSQLiteOpenHelper(this@MainActivity,"Autores",null,1)
                val bd = autor.writableDatabase
                val registros = ContentValues()
                registros.put("nombre", nombre.text.toString())
                registros.put("edad",edad.text.toString().toInt())
                val reg=bd.update("Autores",registros,"codigo=${codigo.text.toString().toInt()}",null)
                if(reg==1){
                    muestraMensaje("Registro actualizado correctamente")
                }
                else{
                    muestraMensaje("Registro no encontrado")
                }

                bd.close()
                limpiar()

            } else{
                muestraMensaje("El campo no puede estar vacio")
            }
        }
        eliminar.setOnClickListener{
            if (!codigo.text.toString().isEmpty()){
                val autor = AutoresSQLiteOpenHelper(this@MainActivity,"Autores",null,1)
                val bd = autor.writableDatabase
                val reg=bd.delete("Autores","codigo=${codigo.text.toString().toInt()}",null)
                if(reg==1){
                    muestraMensaje("Registro eliminado correctamente")
                }
                else{
                    muestraMensaje("No se ha podido eliminar  el registro porque no existe")
                }

                bd.close()
                limpiar()

            } else{
                muestraMensaje("El campo no puede estar vacio")
            }
        }
        mtodos.setOnClickListener{
            val autor=AutoresSQLiteOpenHelper(this@MainActivity,"Autores",null,1)
            val bd=autor.writableDatabase
            val listanombres=ArrayList<String>()
            val regis = bd.rawQuery("Select nombre from Autores",null)

            while(regis.moveToNext()){
                listanombres.add(regis.getString(0))
            }
            val adaptador = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listanombres)
            espin.adapter=adaptador
            bd.close()
        }
        espin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                muestraMensaje("Has seleccionado ${parent!!.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    fun muestraMensaje(x: String){
        Toast.makeText(this,x,Toast.LENGTH_SHORT).show()
    }

}
