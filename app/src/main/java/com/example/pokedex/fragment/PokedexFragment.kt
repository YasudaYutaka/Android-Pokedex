package com.example.pokedex.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokedexBinding

class PokedexFragment : Fragment() {

    private val binding : FragmentPokedexBinding by lazy { FragmentPokedexBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root;
    }

    override fun onStart() {
        super.onStart()

    }

}