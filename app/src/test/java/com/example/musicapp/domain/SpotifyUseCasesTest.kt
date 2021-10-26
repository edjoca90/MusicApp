package com.example.musicapp.domain

import com.example.musicapp.data.model.spotifymodels.TracksList
import com.example.musicapp.fakes.fakeBooleanList
import com.example.musicapp.fakes.fakeFeaturedPlaylists
import com.example.musicapp.fakes.fakeTracksList

class SpotifyUseCasesTest : SpotifyUseCasesInterface {
    override suspend fun getFeaturedPlaylistUseCase(token: String) = fakeFeaturedPlaylists

    override suspend fun getPlaylistUseCase(token: String, id: String) = fakeTracksList

    override suspend fun getUserSavedTracksUseCase(token: String) = fakeTracksList


    override suspend fun saveTrackForUserUseCase(token: String, id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun removeTrackForUserUseCase(token: String, id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserSavedTracks(token: String, idsList: List<String>) = fakeBooleanList

}




