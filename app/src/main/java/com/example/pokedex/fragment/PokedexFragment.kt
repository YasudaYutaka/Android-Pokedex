package com.example.pokedex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonListAdapter
import com.example.pokedex.databinding.FragmentPokedexBinding
import com.example.pokedex.viewmodel.PokemonListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PokedexFragment : Fragment() {

    private val binding : FragmentPokedexBinding by lazy { FragmentPokedexBinding.inflate(layoutInflater) }
    private val viewModel : PokemonListViewModel by inject()
    private val adapter : PokemonListAdapter by lazy { PokemonListAdapter(viewModel) }
    private var recyclerView : RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvPokemons)

        viewModel.successState.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.Main) {
                recyclerView!!.adapter = adapter
                recyclerView!!.layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.errorState.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.Main) {
                Snackbar.make(view, "Erro ao carregar", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.onGetPokemonList(10, 0)

    }

}