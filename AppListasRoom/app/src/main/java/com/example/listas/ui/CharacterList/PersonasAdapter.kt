package com.example.listas.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.listas.R
import com.example.listas.databinding.ItemCharacterBinding
import com.example.listas.ui.CharacterList.Actions

class PersonasAdapter(
    private val actions: Actions
) : androidx.recyclerview.widget.ListAdapter<com.example.appnobasica.domain.modelo.Character, PersonasAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_character, parent, false),actions
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    class ItemViewholder(
        itemView: View,
        private val actions: Actions
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCharacterBinding.bind(itemView)

        fun bind(item: com.example.appnobasica.domain.modelo.Character) = with(binding) {
            tvNombre.text = item.name
            tvApellidos.text = item.description
            logo.load(item.image) {
                crossfade(true)
            }


            buttonBorrar.setOnClickListener {
                actions.onClickBorrar(binding.tvNombre.text.toString())
            }
            buttonUpdate.setOnClickListener {
                actions.onClickEdit(binding.tvNombre.text.toString())
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<com.example.appnobasica.domain.modelo.Character>() {
    override fun areItemsTheSame(
        oldItem: com.example.appnobasica.domain.modelo.Character,
        newItem: com.example.appnobasica.domain.modelo.Character
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: com.example.appnobasica.domain.modelo.Character,
        newItem: com.example.appnobasica.domain.modelo.Character
    ): Boolean {
        return oldItem == newItem
    }
}

