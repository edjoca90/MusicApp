package com.example.musicapp.domain

import com.example.musicapp.data.SpotifyRepository
import javax.inject.Inject

class SpotifyUseCases @Inject constructor(private val repository: SpotifyRepository) : SpotifyUseCasesInterface {
    override suspend fun getFeaturedPlaylistUseCase(token: String) =
        repository.getFeaturedPlaylist(token)

    override suspend fun getPlaylistUseCase(token: String, id: String) =
        repository.getPlaylist(token, id)

    override suspend fun getUserSavedTracksUseCase(token: String) =
        repository.getUserSavedTracks(token)

    override suspend fun checkUserSavedTracks(token: String, idsList: List<String>) =
        repository.checkUserSavedTracks(token, idsList)

    override suspend fun saveTrackForUserUseCase(token: String, id: String?) =
        repository.saveTrackForUser(token, id)

    override suspend fun removeTrackForUserUseCase(token: String, id: String?) =
        repository.removeTrackForUser(token, id)
}


/*class GetFeaturedPlaylistUseCase @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String) = repository.getFeaturedPlaylist(token)
}

class GetPlaylistUseCase @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String, id: String) = repository.getPlaylist(token, id)
}

class GetUserSavedTracksUseCase @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String) = repository.getUserSavedTracks(token)
}

class SaveTrackForUserUseCase @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String, id: String?) = repository.saveTrackForUser(token, id)
}

class RemoveTrackForUserUseCase @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String, id: String?) = repository.removeTrackForUser(token, id)
}

class CheckUserSavedTracks @Inject constructor(private val repository: SpotifyRepository) {
    suspend operator fun invoke(token: String, idsList: List<String>) = repository.checkUserSavedTracks(token, idsList)
}*/