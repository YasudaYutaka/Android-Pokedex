package com.example.pokedex

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var offset : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.successState.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                binding.rvPokemons.adapter = adapter
                binding.rvPokemons.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.pbEndless.visibility = View.GONE
            }
        }

        viewModel.errorState.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                Snackbar.make(binding.root, "Erro ao carregar", Snackbar.LENGTH_LONG)
                    .show()
                binding.pbEndless.visibility = View.GONE
            }
        }

        viewModel.scrollSuccessState.observe(this) {
            viewModel.addPokemonToList(viewModel.newPokemonList)
            binding.pbEndless.visibility = View.GONE
        }

        viewModel.scrollErrorState.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                Snackbar.make(binding.root, "Não foi possível carregar novos Pokémon", Snackbar.LENGTH_LONG)
                    .show()
                binding.pbEndless.visibility = View.GONE
            }
        }

        viewModel.pokemonList.observe(this) {
            adapter.notifyItemRangeInserted(offset, 10)
        }

        viewModel.offset.observe(this) {
            offset = it
        }

        binding.rvPokemons.addOnScrollListener(recyclerViewScrollListener)

        viewModel.onGetPokemonList(10, offset)

    }

    /**
     * Endless Recycler View
     */
    private val recyclerViewScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            viewModel.onScrollingVertically(!binding.rvPokemons.canScrollVertically(1), binding.pbEndless.isVisible, offset, onScroll = { offset ->
                binding.pbEndless.visibility = View.VISIBLE
                viewModel.onGetPokemonListByScroll(10, offset)
            })
        }
    }

}