package com.example.musicapp.data.network

import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpotifyService @Inject constructor(
    private val spotifyApi: SpotifyApiClient,
) {
    //se crea una instancia de RetrofitHelper, luego una funcion que llamará a SpotifyApiClient.
    //Dentro de la función getQuotes() estamos creando una corrutina
    //si un día se desea cambiar los endpoints solo deberemos tocar esta clase
    //esta clase es la puerta de acceso a internet y será llamada por el repositorio

    suspend fun getPlayList(token: String, id: String): TracksList {
        return withContext(Dispatchers.IO) {
            val response = spotifyApi.getPlayList("Bearer $token", id)
            response.body()!!
        }
    }

    suspend fun checkUserSavedTracks(token: String, ids: List<String>): List<Boolean> {
        return withContext(Dispatchers.IO) {
            val response = spotifyApi.checkUserSavedTracks("Bearer $token", ids)
            response
        }
    }

    suspend fun getFeaturedPlayList(token: String): Playlists {
        return withContext(Dispatchers.IO) {
            val response = spotifyApi.getFeaturedPlayList("Bearer $token")
            response.body()!!
        }
    }

    suspend fun getUserSavedTracks(token: String): TracksList {
        return withContext(Dispatchers.IO) {
            val response = spotifyApi.getUserSavedTracks("Bearer $token")
            response.body()!!
        }
    }

    suspend fun saveTrackForUser(token: String, id: String?) {
        return withContext(Dispatchers.IO) {
            spotifyApi.saveTrackForUser("Bearer $token", id)
        }
    }

    suspend fun removeTrackForUser(token: String, id: String?) {
        return withContext(Dispatchers.IO) {
            spotifyApi.removeTrackForUser("Bearer $token", id)
        }
    }


}