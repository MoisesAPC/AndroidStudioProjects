package com.example.actividad7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorEquipos (private val datos: Array<Equipos>, private val clickListener:(Equipos) ->Unit): RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder>() {

    class EquiposViewHolder(val item: View): RecyclerView.ViewHolder(item) {
        val tvNombre = item.findViewById<TextView>(R.id.tvNombre)
        val tvJugadores = item.findViewById<TextView>(R.id.tvJugadores)

        fun bindEquipos(equipos: Equipos){
            tvNombre.text = equipos.nombre
            tvJugadores.text = equipos.njugadores.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.lista_equipos, parent, false) as LinearLayout
        return EquiposViewHolder(item)
    }

    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) {
        val equipos = datos[position]
        holder.bindEquipos(equipos)
        holder.item.setOnClickListener { clickListener(equipos) }
    }

    override fun getItemCount(): Int = datos.size
}