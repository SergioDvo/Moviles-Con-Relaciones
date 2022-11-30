package com.example.listas.ui.UpdateCharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.R
import com.example.listas.databinding.ItemCharacterBinding
import com.example.listas.databinding.ItemHabilidadBinding
import com.example.listas.modelo.Habilidad

class HabilidadesAdapter (
    private var habilidades: List<Habilidad>,
) : RecyclerView.Adapter<HabilidadesAdapter.HabilidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabilidadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HabilidadViewHolder(layoutInflater.inflate(R.layout.item_habilidad, parent, false))
    }


    override fun onBindViewHolder(holder: HabilidadViewHolder, position: Int) {
        holder.render(habilidades[position])
    }

    override fun getItemCount(): Int = habilidades.size


    fun cambiarLista(lista: List<Habilidad>) {
        habilidades = lista
        notifyDataSetChanged()
    }


    inner class HabilidadViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemHabilidadBinding.bind(view)
        fun render(
            habilidad: Habilidad,
        ) {
            with(binding) {
                tvNombre.text = habilidad.nombre
                tvMana.text = habilidad.mana.toString()
            }
        }
    }

}