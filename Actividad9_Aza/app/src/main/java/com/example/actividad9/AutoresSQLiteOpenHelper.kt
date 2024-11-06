package com.example.actividad9

import android.content.Context
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AutoresSQLiteOpenHelper(contexto: Context, nombre: String, factory: SQLiteDatabase.CursorFactory?,version: Int): SQLiteOpenHelper(contexto,nombre,factory,version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE Autores(codigo int primary key, nombre texto, edad int)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}