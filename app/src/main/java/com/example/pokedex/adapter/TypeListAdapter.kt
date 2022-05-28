package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.TypeList
import com.example.pokedex.viewmodel.PokemonTypeViewModel

class TypeListAdapter(private val viewModel : PokemonTypeViewModel) : RecyclerView.Adapter<TypeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = viewModel.getTypeByPosition(position)
        holder.bind(type)
    }

    override fun getItemCount(): Int = viewModel.pokemonListSize

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val tvType : TextView by lazy { itemView.findViewById(R.id.tvType) }
        private val ivType : ImageView by lazy { itemView.findViewById(R.id.ivType) }

        fun bind(type : TypeList) {
            itemView.background = itemView.resources.getDrawable(viewModel.onGetTypeBackgroundColor(type.type.name))
            tvType.text = type.type.name.replaceFirstChar { it.uppercase() }
            Glide.with(itemView).load(viewModel.onGetTypePicture(type.type.name)).into(ivType)
            ivType.setColorFilter(itemView.resources.getColor(R.color.white))
        }
    }

}