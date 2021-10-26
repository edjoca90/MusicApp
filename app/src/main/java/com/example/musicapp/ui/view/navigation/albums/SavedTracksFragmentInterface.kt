package com.example.musicapp.ui.view.navigation.albums

import com.example.musicapp.data.model.spotifymodels.TrackItem

interface SavedTracksFragmentInterface {
    fun onClickFavourite(tracksList: List<TrackItem>, indexItem: Int)
}
