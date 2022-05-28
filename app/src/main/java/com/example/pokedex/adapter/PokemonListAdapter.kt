package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.MainActivityViewModel
import com.example.pokedex.viewmodel.PokemonTypeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PokemonListAdapter(private val viewModel : MainActivityViewModel) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

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
        private val tvPokemonNumber : TextView by lazy { itemView.findViewById(R.id.tvPokemonNumber) }
        private val tvPokemonName : TextView by lazy { itemView.findViewById(R.id.tvPokemonName) }
        private val ivPokemon : ImageView by lazy { itemView.findViewById(R.id.ivPokemon) }
        private val rvPokemonTypes : RecyclerView by lazy { itemView.findViewById(R.id.rvPokemonTypes) }

        fun bind(pokemon: Pokemon) {
            tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
            GlobalScope.launch(Dispatchers.Main) {
                val pokemonID = "/[0-9]+".toRegex().find(pokemon.url)?.value?.split("/")?.get(1)?.toInt()
                tvPokemonNumber.text = itemView.resources.getString(R.string.pokemonNumber, pokemonID.toString())
                val response = viewModel.onGetPokemonById(pokemonID ?: 1);
                response?.let {
                    Glide.with(itemView).load(response.sprites.front_default).into(ivPokemon)
                    itemView.background = itemView.resources.getDrawable(viewModel.onGetBackgroundColorByType(response.types[0].type.name))
                    rvPokemonTypes.adapter = TypeListAdapter(PokemonTypeViewModel(response.types))
                    rvPokemonTypes.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

}