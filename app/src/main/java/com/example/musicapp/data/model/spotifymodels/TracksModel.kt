package com.example.musicapp.data.model.spotifymodels

import com.google.gson.annotations.SerializedName

data class TracksList(
    @SerializedName("items")
    var playListItems: List<TrackItem> = ArrayList(),
)

data class TrackItem(
    @SerializedName("track")
    var track: Track? = null,
    var favorite: Boolean = false,
)

data class Track(
    @SerializedName("album")
    var album: Album? = null,
    @SerializedName("artists")
    var artists: List<Artist> = ArrayList(),
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
)

data class Artist(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
)

data class Album(
    @SerializedName("images")
    val images: List<Images> = ArrayList(),
)

data class Images(
    @SerializedName("url")
    val url: String? = null,
)

