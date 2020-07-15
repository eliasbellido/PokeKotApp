package com.beyondthecode.pokekot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.beyondthecode.pokekot.R
import com.beyondthecode.pokekot.databinding.ItemPokemonBinding
import com.beyondthecode.pokekot.model.Pokemon
import com.beyondthecode.pokekot.ui.details.DetailActivity

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val pokemons: MutableList<Pokemon> = mutableListOf()
    private var onClickedTime = System.currentTimeMillis()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPokemonBinding>(inflater, R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(binding)
    }
    
    fun addPokemonList(pokemonList: List<Pokemon>){
        pokemons.addAll(pokemonList)
        notifyDataSetChanged()
    }
    
    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.binding.apply { 
            this.pokemon = pokemon
            executePendingBindings()
            root.setOnClickListener{
                val currentTime = System.currentTimeMillis()
                if(currentTime - onClickedTime > transformationLayout.duration){
                    onClickedTime = currentTime
                    DetailActivity.startActivity(transformationLayout, pokemon)
                }
            }
        }
    }

    class PokemonViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
}