package com.beyondthecode.pokekot.di

import com.beyondthecode.pokekot.network.PokedexClient
import com.beyondthecode.pokekot.persistence.PokemonDao
import com.beyondthecode.pokekot.persistence.PokemonInfoDao
import com.beyondthecode.pokekot.repository.DetailRepository
import com.beyondthecode.pokekot.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Created by Elías Bellido on 7/15/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

   @Provides
   @ActivityRetainedScoped
   fun provideMainRepository(
       pokedexClient: PokedexClient,
       pokemonDao: PokemonDao
   ): MainRepository {
       return MainRepository(pokedexClient, pokemonDao)
   }

    @Provides
    @ActivityRetainedScoped
    fun provideDetailRepository(
        pokedexClient: PokedexClient,
        pokemonInfoDao: PokemonInfoDao
    ): DetailRepository {
        return DetailRepository(pokedexClient, pokemonInfoDao)
    }
}