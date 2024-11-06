package com.example.ejemplolistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listalen = findViewById<ListView>(R.id.listapro)

        val lenguajes = arrayOf("Ada", "C", "C++", "Go", "Java", "Javascript", "Kotlin", "php", "Ruby")
        val adaptador = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1, lenguajes)
        listalen.adapter = adaptador

        listalen.setOnItemClickListener { parent, view, position, id ->
            val leng = "${parent.getItemAtPosition(position)}"
            showMessage(leng)
        }
    }

    fun showMessage(mensa: String) {
        Toast.makeText(this@MainActivity, mensa, Toast.LENGTH_LONG).show()
    }

}