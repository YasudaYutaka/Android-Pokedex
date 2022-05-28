package com.example.pokedex.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonByIdResponse
import com.example.pokedex.repository.PokemonListRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(private val repository: PokemonListRepository) : ViewModel() {

    private val mutableSuccessState = MutableLiveData<Boolean>()
    val successState : LiveData<Boolean> get() =  mutableSuccessState;
    private val mutableErrorState = MutableLiveData<Boolean>()
    val errorState : LiveData<Boolean> get() = mutableErrorState

    private val mutablePokemonList = MutableLiveData<MutableList<Pokemon>>()
    val pokemonList : LiveData<MutableList<Pokemon>> get() = mutablePokemonList

    val pokemonListSize : Int get() = pokemonList.value!!.size

    fun getPokemonByPosition(position : Int) : Pokemon {
        return pokemonList.value!![position]
    }

    fun onGetPokemonList(limit : Int, offset : Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonList(limit, offset)
                if (response?.results?.size != null) {
                    mutablePokemonList.postValue(response.results)
                    mutableSuccessState.postValue(true)
                } else {
                    mutableErrorState.postValue(true)
                }
            } catch (e : Exception) {
                println(e.message)
                mutableErrorState.postValue(true)
            }
        }
    }

    suspend fun onGetPokemonById(id : Int) : PokemonByIdResponse? {
        return try {
            val response = repository.getPokemonById(id)
            response
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    fun onGetBackgroundColorByType(firstType: String): Int {
        return when(firstType) {
            "bug" -> R.color.background_bug
            "dark" -> R.color.background_dark
            "dragon" -> R.color.background_dragon
            "electric" -> R.color.background_electric
            "fairy" -> R.color.background_fairy
            "fighting" -> R.color.background_fighting
            "fire" -> R.color.background_fire
            "flying" -> R.color.background_flying
            "ghost" -> R.color.background_ghost
            "grass" -> R.color.background_grass
            "ground" -> R.color.background_ground
            "ice" -> R.color.background_ice
            "normal" -> R.color.background_normal
            "poison" -> R.color.background_poison
            "psychic" -> R.color.background_psychic
            "rock" -> R.color.background_rock
            "steel" -> R.color.background_steel
            "water" -> R.color.background_water
            else -> R.color.background_water
        }
    }

}