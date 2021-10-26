package com.example.musicapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.spotifymodels.Playlist
import com.example.musicapp.domain.SpotifyUseCasesInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private var spotifyUseCases: SpotifyUseCasesInterface,
    //private val getFeaturedPlaylistUseCase: GetFeaturedPlaylistUseCase,
) : ViewModel(), AlbumsViewModelInterface {

    private var featuredPlaylist = MutableLiveData<List<Playlist>>()
    private val isLoading = MutableLiveData<Boolean>()
    val featuredPlaylistsLiveData: LiveData<List<Playlist>> get() = featuredPlaylist
    val isLoadingLiveData: LiveData<Boolean> get() = isLoading

    override fun getFeaturedPlayList(token: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = spotifyUseCases.getFeaturedPlaylistUseCase(token)
            //val result = getFeaturedPlaylistUseCase(token)
            featuredPlaylist.postValue(result.listPlaylist!!.listItems)
            isLoading.postValue(false)
        }
    }

}