package com.beyondthecode.pokekot.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.beyondthecode.pokekot.base.DataBindingActivity
import com.beyondthecode.pokekot.databinding.ActivityDetailBinding
import com.beyondthecode.pokekot.R
import com.beyondthecode.pokekot.extensions.onTransformationEndContainerApplyParams
import com.beyondthecode.pokekot.model.Pokemon
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
@AndroidEntryPoint
class DetailActivity : DataBindingActivity() {

    private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)
        val pokemonItem: Pokemon = requireNotNull(intent.getParcelableExtra(EXTRA_POKEMON))
        binding.apply {
            pokemon = pokemonItem
            lifecycleOwner = this@DetailActivity
            vm = viewModel.apply { fetchPokemonInfo(pokemonItem.name) }
        }
    }

    companion object {
        private const val EXTRA_POKEMON = "EXTRA_POKEMON"

        fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon){
            val context = transformationLayout.context
            if(context is Activity){
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(EXTRA_POKEMON, pokemon)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
        }
    }
}