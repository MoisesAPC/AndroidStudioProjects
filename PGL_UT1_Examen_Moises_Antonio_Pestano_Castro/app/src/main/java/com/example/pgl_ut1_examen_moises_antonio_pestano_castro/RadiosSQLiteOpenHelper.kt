package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RadiosSQLiteOpenHelper(contexto: Context, nombre: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(contexto, nombre, factory, version) {

    // Usando el "companion object" + "const val" podemos acceder a estas variables y funciones
    // sin necesidad de instanciar un objeto "ProductosSQLiteOpenHelper"
    // (como el "static" en Java)
    companion object {
        val nombreTablaRadios = "Radios"
        val nombreBaseDeDatos = "Moises"
        const val version = 1
    }

    // Al crear la base de datos, creamos la tabla
    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        baseDeDatos!!.execSQL(
            "CREATE TABLE $nombreTablaRadios (\n" +
                    "\tcodigo integer primary key,\n" +
                    "\tnombre varchar(50),\n" +
                    "\ttipo varchar(50),\n" +
                    "\tnumoyentes int,\n" +
                    "\tfrecuencia double,\n" +
                    "\tnumpremios int\n" +
                    ");\n"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // Retorna -1 si ocurrió un error
    fun insertarRadio(codigo: Int, nombre: String, tipo: String, numoyentes: Int, frecuencia: Double, numpremios: Int): Long {
        val baseDeDatos = this.writableDatabase
        val registrosTablaRadios = ContentValues().apply {
            put("codigo", codigo)
            put("nombre", nombre)
            put("tipo", tipo)
            put("numoyentes", numoyentes)
            put("frecuencia", frecuencia)
            put("numpremios", numpremios)
        }

        val resultado: Long = baseDeDatos.insert(nombreTablaRadios, null, registrosTablaRadios)
        return resultado
    }

    // Retorna una lista con las radios obtenidas dependiendo de su numpremios
    fun buscarRadioPorNumPremios(numpremios: Int): List<Radios> {
        // "mutableListOf" es como "listof", pero permite que se pueda modificar el array
        val listaRadios = mutableListOf<Radios>()

        val baseDeDatos = this.readableDatabase
        val radioDeDB = baseDeDatos.rawQuery("SELECT * FROM Radios WHERE numpremios < '$numpremios' ORDER BY numoyentes ASC", null)

        while (radioDeDB.moveToNext()) {
            val codigo = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("codigo"))
            val nombre = radioDeDB.getString(radioDeDB.getColumnIndexOrThrow("nombre"))
            val tipo = radioDeDB.getString(radioDeDB.getColumnIndexOrThrow("tipo"))
            val numoyentes = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("numoyentes"))
            val frecuencia = radioDeDB.getDouble(radioDeDB.getColumnIndexOrThrow("frecuencia"))
            val numpremios = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("numpremios"))

            val radio = Radios(codigo, nombre, tipo, numoyentes, frecuencia, numpremios)
            listaRadios.add(radio)
        }

        return listaRadios
    }

    fun buscarRadioPorCodigo(codigo: Int): List<Radios> {
        // "mutableListOf" es como "listof", pero permite que se pueda modificar el array
        val listaRadios = mutableListOf<Radios>()

        val baseDeDatos = this.readableDatabase
        val radioDeDB = baseDeDatos.rawQuery("SELECT * FROM Radios WHERE codigo = '$codigo'", null)

        while (radioDeDB.moveToNext()) {
            val codigo = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("codigo"))
            val nombre = radioDeDB.getString(radioDeDB.getColumnIndexOrThrow("nombre"))
            val tipo = radioDeDB.getString(radioDeDB.getColumnIndexOrThrow("tipo"))
            val numoyentes = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("numoyentes"))
            val frecuencia = radioDeDB.getDouble(radioDeDB.getColumnIndexOrThrow("frecuencia"))
            val numpremios = radioDeDB.getInt(radioDeDB.getColumnIndexOrThrow("numpremios"))

            val radio = Radios(codigo, nombre, tipo, numoyentes, frecuencia, numpremios)
            listaRadios.add(radio)
        }

        return listaRadios
    }

    fun eliminarRadio(codigo: Int): Int {
        val baseDeDatos = this.writableDatabase

        return baseDeDatos.delete(nombreTablaRadios, "codigo = $codigo", null)
    }

    fun actualizarNumPremios(numoyentes: Int, numpremiosNuevo: Int): Int {
        val baseDeDatos = this.writableDatabase
        val registrosTablaProductos = ContentValues().apply {
            put("numpremios", numpremiosNuevo)
        }

        val registrosModificados = baseDeDatos.update(
            nombreTablaRadios,
            registrosTablaProductos,
            "numoyentes > $numoyentes",
            null
        )

        return registrosModificados
    }

    fun mostrarNumOyentes(codigo: Int): Int {
        val baseDeDatos = this.readableDatabase
        val cursor = baseDeDatos.rawQuery("SELECT numoyentes FROM $nombreTablaRadios WHERE codigo = $codigo", null)
        return cursor.getInt(0)
    }

    fun cerrarConexion() {
        if (this.readableDatabase.isOpen || this.writableDatabase.isOpen) {
            this.close()
        }
    }
}