package com.example.musicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.spotifymodels.TracksList
import com.example.musicapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TracksViewModel @Inject constructor(
    private var spotifyUseCases: SpotifyUseCasesInterface,
    /*private val getPlaylistUseCase: GetPlaylistUseCase,
    private val checkUserSavedTracks: CheckUserSavedTracks,
    private val saveTrackForUserUseCase: SaveTrackForUserUseCase,
    private val removeTrackForUserUseCase: RemoveTrackForUserUseCase,*/
) : ViewModel() {

    private val playlist = MutableLiveData<TracksList>()
    private val isLoading = MutableLiveData<Boolean>()
    val playlistLiveData: LiveData<TracksList> get() = playlist
    val isLoadingLiveData: LiveData<Boolean> get() = isLoading

    fun saveTrackForUser(token: String, id: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
            spotifyUseCases.saveTrackForUserUseCase(token, id)
            isLoading.postValue(false)
        }
    }

    fun removeTrackForUser(token: String, id: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
            spotifyUseCases.removeTrackForUserUseCase(token, id)
            isLoading.postValue(false)
        }
    }

    fun getPlayList(token: String, id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = spotifyUseCases.getPlaylistUseCase(token, id)
            val idsList = mutableListOf<String>()

            result.playListItems.forEach {
                idsList.add(it.track!!.id!!)
            }

            val listResponse = spotifyUseCases.checkUserSavedTracks(token, idsList)
            for ((index, item) in listResponse.withIndex()) {
                result.playListItems[index].favorite = item
            }

            playlist.postValue(result)
            isLoading.postValue(false)
        }
    }
}