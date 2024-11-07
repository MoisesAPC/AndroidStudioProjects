package com.example.ut1_actividadintegradora

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductosSQLiteOpenHelper(contexto: Context, nombre: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(contexto, nombre, factory, version) {

    // Al crear la base de datos, creamos la tabla
    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        baseDeDatos!!.execSQL(
            "CREATE TABLE Productos (\n" +
                    "\tid integer primary key,\n" +
                    "\tnombre varchar(50),\n" +
                    "\tmarca varchar(50),\n" +
                    "\tprecio double,\n" +
                    "\tcategoria_id integer,\n" +
                    "\tdisponible boolean\n" +
                ");\n"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}