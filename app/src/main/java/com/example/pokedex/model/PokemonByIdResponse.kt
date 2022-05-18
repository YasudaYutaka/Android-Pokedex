package com.example.pokedex.model

data class PokemonByIdResponse(
    val sprites : Sprites,
    val types : List<TypeList>
)

data class Sprites(
    val front_default : String
)

data class TypeList(
    val slot : Int,
    val type : Type
)

data class Type(
    val name : String,
    val url : String
)
