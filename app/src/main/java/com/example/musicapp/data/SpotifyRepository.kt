package com.example.musicapp.data

import com.example.musicapp.data.model.SpotifyProvider
import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList
import com.example.musicapp.data.network.SpotifyService
import javax.inject.Inject

class SpotifyRepository @Inject constructor(
    private val spotifyApi: SpotifyService,
    private val spotifyProvider: SpotifyProvider,
) : SpotifyRepositoryInterface {

    // el repositorio se encarga de obtener la informacion en este caso del SpotifyApi
    override suspend fun getPlaylist(token: String, id: String): TracksList {
        val response = spotifyApi.getPlayList(token, id)
        spotifyProvider.playlist = response
        return response
    }

    override suspend fun checkUserSavedTracks(token: String, idsList: List<String>): List<Boolean> {
        return spotifyApi.checkUserSavedTracks(token, idsList)
    }

    override suspend fun getFeaturedPlaylist(token: String): Playlists {
        val response = spotifyApi.getFeaturedPlayList(token)
        spotifyProvider.featuredPlayList = response
        return response
    }

    override suspend fun getUserSavedTracks(token: String): TracksList {
        val response = spotifyApi.getUserSavedTracks(token)
        spotifyProvider.savedPlaylist = response
        return response
    }

    override suspend fun saveTrackForUser(token: String, id: String?) {
        spotifyApi.saveTrackForUser(token, id)
    }

    override suspend fun removeTrackForUser(token: String, id: String?) {
        spotifyApi.removeTrackForUser(token, id)
    }


}