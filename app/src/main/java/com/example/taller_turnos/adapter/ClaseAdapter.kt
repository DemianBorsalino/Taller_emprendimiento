package com.example.taller_turnos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taller_turnos.R
import com.example.taller_turnos.model.Clase
import android.view.View
import android.widget.TextView

class ClaseAdapter(
    private var clases: List<Clase>,
    private val onClick: (Clase) -> Unit
) : RecyclerView.Adapter<ClaseAdapter.ClaseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_class, parent, false)
        return ClaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClaseViewHolder, position: Int) {
        holder.bind(clases[position])
        holder.itemView.setOnClickListener {
            onClick(clases[position])
        }
    }

    override fun getItemCount(): Int = clases.size


    fun actualizarLista(nuevaLista: List<Clase>) {
        clases = nuevaLista
        notifyDataSetChanged()
    }

    class ClaseViewHolder(itemView: android.view.View)
        : RecyclerView.ViewHolder(itemView) {

        private val tvNombre = itemView.findViewById<TextView>(R.id.tvNombre)
        private val tvNombreMaestra = itemView.findViewById<TextView>(R.id.tvNombreMaestra)
        private val tvAlumnas = itemView.findViewById<TextView>(R.id.tvAlumnas)
        private val tvCupo = itemView.findViewById<TextView>(R.id.tvCupo)

        fun bind(clase: Clase) {
            tvNombre.text = clase.nombre
            tvNombreMaestra.text = "Maestra: ${clase.nombreMaestra}"
            tvAlumnas.text = "Inscriptos: ${clase.inscriptos}"
            tvCupo.text = "${clase.inscriptos}/${clase.cupoMaximo}"
        }
    }


}