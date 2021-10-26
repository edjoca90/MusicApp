package com.example.musicapp.data.model.spotifymodels

import com.google.gson.annotations.SerializedName

data class Playlists(
    @SerializedName("playlists")
    var listPlaylist: PlaylistsItems? = null,
)

data class PlaylistsItems(
    @SerializedName("items")
    var listItems: List<Playlist> = ArrayList(),
)

data class Playlist(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("images")
    var images: List<ImagePlaylist> = ArrayList(),
)

data class ImagePlaylist(
    @SerializedName("url")
    var url: String? = null,
)

