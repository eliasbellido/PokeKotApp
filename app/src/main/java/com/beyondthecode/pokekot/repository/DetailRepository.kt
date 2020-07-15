package com.beyondthecode.pokekot.repository

import androidx.lifecycle.MutableLiveData
import com.beyondthecode.pokekot.model.PokemonInfo
import com.beyondthecode.pokekot.network.PokedexClient
import com.beyondthecode.pokekot.persistence.PokemonInfoDao
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
class DetailRepository @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao
) : Repository {

    suspend fun fetchPokemonInfo(
        name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<PokemonInfo>()
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        pokemonInfo?.let {
            onSuccess()
        } ?: run {
            pokedexClient.fetchPokemonInfo(name = name) {
                it.onSuccess {
                    data?.let { response ->
                        liveData.postValue(response)
                        pokemonInfoDao.insertPokemonInfo(response)
                        onSuccess()
                    }
                }

                    .onError {
                        onError(message())
                    }

                    .onException {
                        onError(message())
                    }
            }
        }

        liveData.apply { postValue(pokemonInfo) }
    }
}