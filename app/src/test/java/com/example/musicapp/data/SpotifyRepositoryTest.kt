package com.example.musicapp.data

import com.example.musicapp.data.SpotifyRepositoryInterface
import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


class SpotifyRepositoryTest : SpotifyRepositoryInterface {
    override suspend fun getPlaylist(token: String, id: String): TracksList {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserSavedTracks(token: String, idsList: List<String>): List<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getFeaturedPlaylist(token: String): Playlists {
        TODO("Not yet implemented")
    }

    override suspend fun getUserSavedTracks(token: String): TracksList {
        TODO("Not yet implemented")
    }

    override suspend fun saveTrackForUser(token: String, id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun removeTrackForUser(token: String, id: String?) {
        TODO("Not yet implemented")
    }
}