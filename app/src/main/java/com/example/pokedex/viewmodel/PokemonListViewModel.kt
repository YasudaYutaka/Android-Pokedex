package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.Pokemon
import com.example.pokedex.repository.PokemonListRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PokemonListViewModel(private val repository: PokemonListRepository) : ViewModel() {

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

}