package com.example.musicapp.data.network

import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList
import retrofit2.Response
import retrofit2.http.*

interface SpotifyApiClient {
    //se utiliza suspend pues las consultas se realizan en Corrutinas

    @GET("/v1/playlists/{id}/tracks")
    suspend fun getPlayList(
        @Header("authorization") auth: String,
        @Path("id") id: String?,
    ): Response<TracksList>

    @GET("/v1/browse/featured-playlists")
    suspend fun getFeaturedPlayList(
        @Header("authorization") auth: String,
    ): Response<Playlists>

    @GET("/v1/me/tracks")
    suspend fun getUserSavedTracks(
        @Header("authorization") auth: String,
    ): Response<TracksList>

    @PUT("/v1/me/tracks")
    suspend fun saveTrackForUser(
        @Header("authorization") auth: String,
        @Query("ids") ids: String?,
    )

    @DELETE("/v1/me/tracks")
    suspend fun removeTrackForUser(
        @Header("authorization") auth: String,
        @Query("ids") id: String?,
    )

    @GET("/v1/me/tracks/contains")
    suspend fun checkUserSavedTracks(
        @Header("authorization") auth: String,
        @Query("ids") ids: List<String>,
    ): List<Boolean>

}