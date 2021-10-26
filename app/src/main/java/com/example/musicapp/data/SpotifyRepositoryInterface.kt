package com.example.musicapp.data

import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList

interface SpotifyRepositoryInterface {
    suspend fun getPlaylist(token: String, id: String): TracksList
    suspend fun checkUserSavedTracks(token: String, idsList: List<String>): List<Boolean>
    suspend fun getFeaturedPlaylist(token: String): Playlists
    suspend fun getUserSavedTracks(token: String): TracksList
    suspend fun saveTrackForUser(token: String, id: String?)
    suspend fun removeTrackForUser(token: String, id: String?)
}