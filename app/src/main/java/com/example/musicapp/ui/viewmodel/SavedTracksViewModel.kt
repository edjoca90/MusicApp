package com.example.musicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.spotifymodels.TracksList
import com.example.musicapp.domain.SpotifyUseCasesInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedTracksViewModel @Inject constructor(
    private var spotifyUseCases: SpotifyUseCasesInterface,
    /*private val getUserSavedTracksUseCase: GetUserSavedTracksUseCase,
    private val removeTrackForUserUseCase: RemoveTrackForUserUseCase,*/
) : ViewModel() {

    private val savedTracks = MutableLiveData<TracksList>()
    private val isLoading = MutableLiveData<Boolean>()
    val savedTracksLiveData: LiveData<TracksList> get() = savedTracks
    val isLoadingLiveData: LiveData<Boolean> get() = isLoading

    fun getUserSavedTracks(token: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = spotifyUseCases.getUserSavedTracksUseCase(token)
            savedTracks.postValue(result)
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
}