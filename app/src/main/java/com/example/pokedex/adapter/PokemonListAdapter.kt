package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.PokemonListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PokemonListAdapter(private val viewModel : PokemonListViewModel) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = viewModel.getPokemonByPosition(position)
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = viewModel.pokemonListSize

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val tvPokemonName : TextView by lazy { itemView.findViewById(R.id.tvPokemonName) }
        private val tvType : TextView by lazy { itemView.findViewById(R.id.tvType) }

        fun bind(pokemon: Pokemon) {
            tvPokemonName.text = pokemon.name
            GlobalScope.launch(Dispatchers.Main) {
                val pokemonID = "/[0-9]+".toRegex().find(pokemon.url)?.value?.split("/")?.get(1)?.toInt()
                val response = viewModel.onGetPokemonById(pokemonID ?: 1);
                response?.let { tvType.text = response.types[0].toString() }
            }
        }
    }

}