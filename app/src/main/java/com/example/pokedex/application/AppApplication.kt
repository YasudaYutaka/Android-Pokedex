package com.example.pokedex.application

import android.app.Application
import com.example.pokedex.repository.PokemonListRepository
import com.example.pokedex.retrofit.RetrofitInstance
import com.example.pokedex.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single { RetrofitInstance.createService() }
            factory { PokemonListRepository(get()) }
            viewModel { MainActivityViewModel(get()) }
        }

        startKoin {
            androidContext(this@AppApplication)
            modules(myModules)
        }

    }

}