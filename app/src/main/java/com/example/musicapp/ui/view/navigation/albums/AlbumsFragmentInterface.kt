package com.example.musicapp.ui.view.navigation.albums

import com.example.musicapp.data.model.spotifymodels.Playlist

interface AlbumsFragmentInterface {
    fun onClickPlayList(playListEntity: Playlist)
}