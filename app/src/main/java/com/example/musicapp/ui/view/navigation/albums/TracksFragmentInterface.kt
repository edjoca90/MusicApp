package com.example.musicapp.ui.view.navigation.albums

import com.example.musicapp.data.model.spotifymodels.TrackItem

interface TracksFragmentInterface {
    fun onClickFavourite(tracksList: List<TrackItem>, indexItem: Int)
}