package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.fragment.FavoritesFragment
import com.example.pokedex.fragment.PokedexFragment
import com.example.pokedex.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Start with PokedexFragment
        replaceFragment(PokedexFragment(), "Pokédex")

        // bottom navigation config
        binding.bottomNavigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener viewModel.onSelectMenuItem(it.itemId)
        }

        viewModelStateObserver()

    }

    private fun viewModelStateObserver() {
        viewModel.pokedex.observe(this) {
            replaceFragment(PokedexFragment(), "Pokédex")
        }
        viewModel.favorites.observe(this) {
            replaceFragment(FavoritesFragment(), "Favorites")
        }
    }

    private fun replaceFragment(fragment: Fragment, title : String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
        setTitle(title)
    }

}