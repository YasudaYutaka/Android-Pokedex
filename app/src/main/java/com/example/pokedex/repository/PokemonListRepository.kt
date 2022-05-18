package com.example.pokedex.repository

import com.example.pokedex.model.PokemonByIdResponse
import com.example.pokedex.model.PokemonListResponse
import com.example.pokedex.retrofit.ServiceInterface

class PokemonListRepository(private val service : ServiceInterface? = null) {

    suspend fun getPokemonList(limit : Int, offset : Int) : PokemonListResponse? {
        val call = service?.getPokemonByLimitAndOffset(limit, offset)
        return if(call!!.isSuccessful && call.body() != null) call.body()
            else null
    }

    suspend fun getPokemonById(pokemonID : Int) : PokemonByIdResponse? {
        val call = service?.getPokemonById(pokemonID)
        return if(call!!.isSuccessful) call.body()
            else null
    }

}