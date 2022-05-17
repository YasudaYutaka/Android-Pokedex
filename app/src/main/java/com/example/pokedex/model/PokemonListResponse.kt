package com.example.pokedex.model

data class PokemonListResponse(
    val count : Int,
    val next : String?,
    val previous : String?,
    val results : MutableList<Pokemon>
)

data class Pokemon(
    val name : String,
    val url : String
)