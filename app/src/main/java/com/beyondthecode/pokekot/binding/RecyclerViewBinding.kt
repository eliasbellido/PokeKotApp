package com.beyondthecode.pokekot.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beyondthecode.pokekot.model.Pokemon
import com.beyondthecode.pokekot.ui.adapter.PokemonAdapter
import com.beyondthecode.pokekot.ui.main.MainViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator

/**
 * Created by Elías Bellido on 7/15/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("paginationPokemonList")
fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel){
    RecyclerViewPaginator(
        recyclerView = view,
        isLoading = { viewModel.isLoading.get() },
        loadMore = { viewModel.fetchPokemonList(it) },
        onLast = { false }
    ).run {
        threshold = 8
    }
}

@BindingAdapter("adapterPokemonList")
fun bindAdapterPokemonList(view: RecyclerView, pokemonList: List<Pokemon>?) {
    pokemonList?.let {
        (view.adapter as? PokemonAdapter)?.addPokemonList(it)
    }
}