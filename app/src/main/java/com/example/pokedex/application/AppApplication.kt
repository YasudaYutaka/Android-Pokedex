package com.example.pokedex.application

import android.app.Application
import com.example.pokedex.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            viewModel { MainActivityViewModel() }
        }

        startKoin {
            androidContext(this@AppApplication)
            modules(myModules)
        }

    }

}