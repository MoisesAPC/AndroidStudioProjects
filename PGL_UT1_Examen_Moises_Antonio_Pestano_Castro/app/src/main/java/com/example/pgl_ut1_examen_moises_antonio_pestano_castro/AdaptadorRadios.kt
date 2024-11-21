package com.example.pgl_ut1_examen_moises_antonio_pestano_castro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorRadios(private val datos: List<Radios>?, private val clickListener: (Radios) -> Unit) :
    RecyclerView.Adapter<AdaptadorRadios.RadiosViewHolder>() {

    class RadiosViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val nomtv = item.findViewById<TextView>(R.id.nomtv)
        val numOyentes_tv = item.findViewById<TextView>(R.id.numOyentes_tv)
        val tipotv = item.findViewById<TextView>(R.id.tipotv)
        val frecuenciatv = item.findViewById<TextView>(R.id.frecuenciatv)

        fun bindRadios(radios: Radios) {
            nomtv.text = "Nombre: ${radios.nombre}"
            numOyentes_tv.text = "Número de oyentes: ${radios.numoyentes}"
            tipotv.text = "Tipo: ${radios.tipo}"
            frecuenciatv.text = "Frecuencia: ${radios.frecuencia}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadiosViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.activity_num_oyenes_mostrar_entrada, parent, false) as LinearLayout
        return RadiosViewHolder(item)
    }

    override fun onBindViewHolder(holder: RadiosViewHolder, position: Int) {
        val productos = datos!![position]
        holder.bindRadios(productos)
        holder.item.setOnClickListener { clickListener(productos) }
    }

    override fun getItemCount(): Int = datos!!.size
}