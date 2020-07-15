package com.beyondthecode.pokekot.repository

import androidx.lifecycle.MutableLiveData
import com.beyondthecode.pokekot.model.Pokemon
import com.beyondthecode.pokekot.network.PokedexClient
import com.beyondthecode.pokekot.persistence.PokemonDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class MainRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao
) : Repository {

    suspend fun fetchPokemonList(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<List<Pokemon>>()
        var pokemonList = pokemonDao.getPokemonList(page)
        if(pokemonList.isEmpty()) {
            pokedexClient.fetchPokemonList(page = page) {
                //handle the case when the API request gets a success response.
                it.onSuccess {
                    data?.let { response ->
                        pokemonList = response.results
                        pokemonList.forEach{ pokemon -> pokemon.page = page }
                        liveData.postValue(pokemonList)
                        pokemonDao.insertPokemonList(pokemonList)
                        onSuccess()
                    }
                }
                //handle the case when there's error response.
                //e.g. internal server error.
                    .onError {
                        onError(message())
                    }

                    .onException {
                        onError(message())
                    }
            }
        } else {
            onSuccess()
        }

        liveData.apply {  postValue(pokemonList) }
    }
}