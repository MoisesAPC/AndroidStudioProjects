package com.example.ut1_actividadintegradora

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorProductos(private val datos: List<Productos>, private val clickListener: (Productos) -> Unit) :
    RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder>() {

    class ProductosViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val tvNombre = item.findViewById<TextView>(R.id.tvNombre)
        val tvMarca = item.findViewById<TextView>(R.id.tvMarca)
        val tvPrecio = item.findViewById<TextView>(R.id.tvPrecio)
        val tvCategoria = item.findViewById<TextView>(R.id.tvCategoria)
        val tvDisponible = item.findViewById<CheckBox>(R.id.tvDisponible)

        fun bindProductos(productos: Productos) {
            tvNombre.text = "Nombre: ${productos.nombre}"
            tvMarca.text = "Marca: ${productos.marca}"
            tvPrecio.text = "Precio: ${productos.precio}€"

            val nombreCategoria = ProductosSQLiteOpenHelper.obtenerNombreCategoria(productos.categoria_id)
            tvCategoria.text = "Categoría: ${nombreCategoria}"

            if (productos.disponible)
                tvDisponible.isChecked = true
            else
                tvDisponible.isChecked = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.activity_listar_producto_entrada, parent, false) as LinearLayout
        return ProductosViewHolder(item)
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val productos = datos[position]
        holder.bindProductos(productos)
        holder.item.setOnClickListener { clickListener(productos) }
    }

    override fun getItemCount(): Int = datos.size
}