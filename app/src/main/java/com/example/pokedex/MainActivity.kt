package com.example.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.adapter.PokemonListAdapter
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainActivityViewModel by inject()
    private val adapter : PokemonListAdapter by lazy { PokemonListAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.successState.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                binding.rvPokemons.adapter = adapter
                binding.rvPokemons.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

        viewModel.errorState.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                Snackbar.make(binding.root, "Erro ao carregar", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.onGetPokemonList(10, 0)

    }

}