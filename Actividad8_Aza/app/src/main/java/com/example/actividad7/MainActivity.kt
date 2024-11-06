package com.example.actividad7

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        val tool = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tool)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.persistencia ->{
                preferencias()
                return true
            }
            R.id.ficheros -> {
                fichero()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun preferencias() {

        val intent = Intent(this, Persistencias::class.java)
        startActivity(intent)
    }

    private fun fichero() {
        val intent = Intent(this, Ficheros::class.java)
        startActivity(intent)
    }
}