package com.example.musicapp.domain

import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList

interface SpotifyUseCasesInterface {
    suspend fun getFeaturedPlaylistUseCase(token: String): Playlists
    suspend fun getPlaylistUseCase(token: String, id: String): TracksList
    suspend fun getUserSavedTracksUseCase(token: String): TracksList
    suspend fun saveTrackForUserUseCase(token: String, id: String?)
    suspend fun removeTrackForUserUseCase(token: String, id: String?)
    suspend fun checkUserSavedTracks(token: String, idsList: List<String>): List<Boolean>
}