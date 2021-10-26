package com.example.musicapp.core.di

import com.example.musicapp.domain.SpotifyUseCases
import com.example.musicapp.domain.SpotifyUseCasesInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BroadcastModule {
    @Binds
    fun provideBroadcastBus(inst: SpotifyUseCases): SpotifyUseCasesInterface
}