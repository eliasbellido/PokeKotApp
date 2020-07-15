package com.beyondthecode.pokekot.ui.main

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.switchMap
import com.beyondthecode.pokekot.base.LiveCoroutinesViewModel
import com.beyondthecode.pokekot.model.Pokemon
import com.beyondthecode.pokekot.repository.MainRepository
import timber.log.Timber

/**
 * Created by Elías Bellido on 7/14/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    private var pokemonFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
    val pokemonListLiveData: LiveData<List<Pokemon>>

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("init MainViewModel")

        pokemonListLiveData = pokemonFetchingLiveData.switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                this.mainRepository.fetchPokemonList(
                    page = it,
                    onSuccess = { isLoading.set(false) },
                    onError = { toastLiveData.postValue(it) })
            }
        }
    }

    fun fetchPokemonList(page: Int) {
        pokemonFetchingLiveData.value = page
    }
}