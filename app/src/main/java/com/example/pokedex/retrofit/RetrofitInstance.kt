package com.example.pokedex.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var retrofitInstance : Retrofit? = null
    val retrofit : Retrofit
        get() = retrofitInstance ?: createRetrofit()

    private var serviceInstance : ServiceInterface? = null
    val service : ServiceInterface
        get() = serviceInstance ?: createService()

    fun createService() : ServiceInterface {
        serviceInstance = retrofit.create(ServiceInterface::class.java)
        return serviceInstance!!
    }

    private const val BASE_URL = "https://pokeapi.co/"

    fun createRetrofit() : Retrofit {
        retrofitInstance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitInstance!!
    }

}