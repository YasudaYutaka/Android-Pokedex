package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.R

class MainActivityViewModel : ViewModel() {

    private val mutablePokedex : MutableLiveData<Boolean> = MutableLiveData()
    val pokedex : LiveData<Boolean> get() = mutablePokedex
    private val mutableFavorites : MutableLiveData<Boolean> = MutableLiveData()
    val favorites : LiveData<Boolean> get() = mutableFavorites

    // Retorna o id selecionado
    fun onSelectMenuItem(id : Int) : Boolean {
        return when(id) {
            R.id.menu_item_pokedex -> {
                mutablePokedex.postValue(true)
                true
            }
            R.id.menu_item_favorites -> {
                mutableFavorites.postValue(true)
                true
            }
            else -> false
        }
    }

}