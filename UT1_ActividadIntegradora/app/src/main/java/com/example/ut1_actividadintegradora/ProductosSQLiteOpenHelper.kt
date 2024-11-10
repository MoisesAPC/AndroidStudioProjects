package com.example.ut1_actividadintegradora

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ProductosSQLiteOpenHelper(contexto: Context, nombre: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(contexto, nombre, factory, version) {

    // Creamos una pequeña data class que asocia cada nombre de categoría con su ID
    data class Categoria(val id: Int, val nombre: String)

    // Usando el "companion object" + "const val" podemos acceder a estas variables y funciones
    // sin necesidad de instanciar un objeto "ProductosSQLiteOpenHelper"
    // (como el "static" en Java)
    companion object {
        const val nombreBaseDeDatos = "Moises"
        const val nombreTablaProductos = "Productos"

        // Inicializamos el array de categorías con su ID y su nombre
        val categorias = listOf(
            Categoria(0, "Comida"),
            Categoria(1, "Ropa"),
            Categoria(2, "Electrónica"),
            Categoria(3, "Lectura"),
            Categoria(4, "Otros")
        )

        // Obtiene el nombre de la categoría en base a su ID
        fun obtenerNombreCategoria(categoriaId: Int): String {
            return categorias.find { it.id == categoriaId }?.nombre ?: "Categoría desconocida"
        }

        const val version = 1
    }

    // Al crear la base de datos, creamos la tabla
    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        baseDeDatos!!.execSQL(
            "CREATE TABLE $nombreTablaProductos (\n" +
                    "\tid integer primary key autoincrement,\n" +
                    "\tnombre varchar(50) unique,\n" +
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

    // Retorna -1 si ocurrió un error
    fun insertarProducto(nombre: String, marca: String, precio: Double, categoria_id: Int, disponible: Boolean): Long {
        val baseDeDatos = this.writableDatabase
        val registrosTablaProductos = ContentValues().apply {
            put("nombre", nombre)
            put("marca", marca)
            put("precio", precio)
            put("categoria_id", categoria_id)
            put("disponible", disponible)
        }

        val resultado: Long = baseDeDatos.insert(nombreTablaProductos, null, registrosTablaProductos)
        debugMostrarProductos()
        return resultado
    }

    // (Asumimos que todos los productos tienen un nombre único)
    // Retorna el objeto de la data class "Productos" encontrado, o "null" en caso contrario
    fun buscarProductoPorNombre(nombre: String): Productos? {
        // Es necesario el "?" para que podamos asignarle el valor null por defecto
        var producto: Productos? = null

        val baseDeDatos = this.readableDatabase
        val productoDeBD = baseDeDatos.rawQuery("SELECT * FROM Productos WHERE nombre = '$nombre'", null)

        if (productoDeBD.moveToFirst()) {
            val id = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("id"))
            val nombreProducto = productoDeBD.getString(productoDeBD.getColumnIndexOrThrow("nombre"))
            val marca = productoDeBD.getString(productoDeBD.getColumnIndexOrThrow("marca"))
            val precio = productoDeBD.getDouble(productoDeBD.getColumnIndexOrThrow("precio"))
            val categoriaId = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("categoria_id"))
            val disponible = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("disponible")) == 1

            producto = Productos(id, nombreProducto, marca, precio, categoriaId, disponible)
        }

        return producto
    }

    // Retorna la lista con los productos
    fun buscarProductos(): List<Productos> {
        // "mutableListOf" es como "listof", pero permite que se pueda modificar el array
        val listaProductos = mutableListOf<Productos>()

        val baseDeDatos = this.readableDatabase
        val productoDeBD = baseDeDatos.rawQuery("SELECT * FROM Productos", null)

        while (productoDeBD.moveToNext()) {
            val id = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("id"))
            val nombreProducto = productoDeBD.getString(productoDeBD.getColumnIndexOrThrow("nombre"))
            val marca = productoDeBD.getString(productoDeBD.getColumnIndexOrThrow("marca"))
            val precio = productoDeBD.getDouble(productoDeBD.getColumnIndexOrThrow("precio"))
            val categoriaId = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("categoria_id"))
            val disponible = productoDeBD.getInt(productoDeBD.getColumnIndexOrThrow("disponible")) == 1

            val producto = Productos(id, nombreProducto, marca, precio, categoriaId, disponible)
            listaProductos.add(producto)
        }
        productoDeBD.close()

        debugMostrarProductos()

        return listaProductos
    }

    // Retorna un número mayor o igual a 1 si la modificación fue correcta, o 0 en caso contrario
    fun modificarProducto(id: Int, nombre: String, marca: String, precio: Double, categoria_id: Int, disponible: Boolean): Int {
        val baseDeDatos = this.writableDatabase
        val registrosTablaProductos = ContentValues().apply {
            put("id", id)
            put("nombre", nombre)
            put("marca", marca)
            put("precio", precio)
            put("categoria_id", categoria_id)
            put("disponible", disponible)
        }

        val registrosModificados = baseDeDatos.update(
            nombreTablaProductos,
            registrosTablaProductos,
            "id = $id",
            null
        )

        debugMostrarProductos()
        return registrosModificados
    }

    // Retorna 1 si la eliminación fue correcta, o 0 en caso contrario
    fun eliminarProducto(id: Int): Int {
        val baseDeDatos = this.writableDatabase

        return baseDeDatos.delete(nombreTablaProductos, "id = $id", null)
    }

    // Retorna 1 si la eliminación fue correcta, o 0 en caso contrario
    fun eliminarTodosLosProductos(): Int {
        val baseDeDatos = this.writableDatabase

        return baseDeDatos.delete(nombreTablaProductos, "", null)
    }

    fun cerrarConexion() {
        if (this.readableDatabase.isOpen || this.writableDatabase.isOpen) {
            this.close()
        }
    }

    // Muestra el contenido de la tabla "Productos". Yo solo lo uso para depurar
    // Usaremos Logcat para ver la salida por consola (yo lo uso porque la aplicación la pruebo en un teléfono real)
    // View > Tools Windows > Logcat
    private fun debugMostrarProductos() {
        val baseDeDatos = this.readableDatabase
        // Obtengo el cursor que usaré para recorrer la base de daots
        val cursor = baseDeDatos.rawQuery("SELECT * FROM $nombreTablaProductos", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val marca = cursor.getString(cursor.getColumnIndexOrThrow("marca"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"))
                val categoriaId = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_id"))
                val disponible = cursor.getInt(cursor.getColumnIndexOrThrow("disponible")) == 1

                // Imprimo los contenidos de cada fila de la base de datos
                Log.d("DB_CONTENT", "ID: $id, Nombre: $nombre, Marca: $marca, Precio: $precio, Categoria ID: $categoriaId, Disponible: $disponible")
            // Vamos a la siguiente file con "cursor.moveToNext()"
            } while (cursor.moveToNext())
        }

        cursor.close()
    }
}