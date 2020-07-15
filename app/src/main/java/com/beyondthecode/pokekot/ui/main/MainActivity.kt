package com.beyondthecode.pokekot.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.beyondthecode.pokekot.R
import com.beyondthecode.pokekot.base.DataBindingActivity
import com.beyondthecode.pokekot.databinding.ActivityMainBinding
import com.beyondthecode.pokekot.ui.adapter.PokemonAdapter
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    val viewModel by viewModels<MainViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = PokemonAdapter()
            vm = viewModel.apply { fetchPokemonList(0) }
        }
    }
}