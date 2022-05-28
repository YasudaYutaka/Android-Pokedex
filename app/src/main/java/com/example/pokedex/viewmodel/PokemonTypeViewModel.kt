package com.example.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokedex.R
import com.example.pokedex.model.TypeList

class PokemonTypeViewModel(private val typeList : List<TypeList>) : ViewModel() {

    val pokemonListSize = typeList.size

    fun getTypeByPosition(position : Int) : TypeList {
        return typeList[position]
    }

    fun onGetTypeBackgroundColor(firstType: String): Int {
        return when(firstType) {
            "bug" -> R.color.type_background_bug
            "dark" -> R.color.type_background_dark
            "dragon" -> R.color.type_background_dragon
            "electric" -> R.color.type_background_electric
            "fairy" -> R.color.type_background_fairy
            "fighting" -> R.color.type_background_fighting
            "fire" -> R.color.type_background_fire
            "flying" -> R.color.type_background_flying
            "ghost" -> R.color.type_background_ghost
            "grass" -> R.color.type_background_grass
            "ground" -> R.color.type_background_ground
            "ice" -> R.color.type_background_ice
            "normal" -> R.color.type_background_normal
            "poison" -> R.color.type_background_poison
            "psychic" -> R.color.type_background_psychic
            "rock" -> R.color.type_background_rock
            "steel" -> R.color.type_background_steel
            "water" -> R.color.type_background_water
            else -> R.color.type_background_water
        }
    }

    fun onGetTypePicture(type : String): Int {
        return when(type) {
            "bug" -> R.drawable.ic_bug
            "dark" -> R.drawable.ic_dark
            "dragon" -> R.drawable.ic_dragon
            "electric" -> R.drawable.ic_electric
            "fairy" -> R.drawable.ic_fairy
            "fighting" -> R.drawable.ic_fighting
            "fire" -> R.drawable.ic_fire
            "flying" -> R.drawable.ic_flying
            "ghost" -> R.drawable.ic_ghost
            "grass" -> R.drawable.ic_grass
            "ground" -> R.drawable.ic_ground
            "ice" -> R.drawable.ic_ice
            "normal" -> R.drawable.ic_normal
            "poison" -> R.drawable.ic_poison
            "psychic" -> R.drawable.ic_psychic
            "rock" -> R.drawable.ic_rock
            "steel" -> R.drawable.ic_steel
            "water" -> R.drawable.ic_water
            else -> R.drawable.ic_water
        }
    }

}