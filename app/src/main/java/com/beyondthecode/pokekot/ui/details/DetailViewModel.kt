package com.beyondthecode.pokekot.ui.details

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.beyondthecode.pokekot.base.LiveCoroutinesViewModel
import com.beyondthecode.pokekot.model.PokemonInfo
import com.beyondthecode.pokekot.repository.DetailRepository
import timber.log.Timber

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class DetailViewModel @ViewModelInject constructor(
    private val detailRepository: DetailRepository
) : LiveCoroutinesViewModel() {

    private var pokemonFetchingLiveData: MutableLiveData<String> = MutableLiveData()
    val pokemonInfoLiveData: LiveData<PokemonInfo>

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("init DetailViewModel")

        pokemonInfoLiveData = pokemonFetchingLiveData.switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                detailRepository.fetchPokemonInfo(
                    name = it,
                    onSuccess = { isLoading.set(false) },
                    onError = { toastLiveData.postValue(it) })

            }
        }
    }

    fun fetchPokemonInfo(name: String) {
        pokemonFetchingLiveData.value = name
    }
}