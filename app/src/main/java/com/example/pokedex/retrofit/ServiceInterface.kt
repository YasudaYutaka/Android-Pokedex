package com.example.pokedex.retrofit

import com.example.pokedex.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {

    @GET("api/v2/pokemon")
    suspend fun getPokemonByLimitAndOffset(@Query("limit") limit : Int, @Query("offset") offset : Int) : Response<PokemonListResponse>

}