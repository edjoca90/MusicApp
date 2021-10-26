package com.example.musicapp.core.di

import com.example.musicapp.data.network.SpotifyApiClient
import com.example.musicapp.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//InstallIn maneja el alcanze de la inyeccion, en este caso
// retrofit es algo generico por lo que el alcanze sera a nivel de aplicacion
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSpotifyApiClient(retrofit: Retrofit): SpotifyApiClient{
        return retrofit.create(SpotifyApiClient::class.java)
    }
}