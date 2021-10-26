package com.example.musicapp.fakes

import com.example.musicapp.data.model.spotifymodels.*

val fakeAlbum = Album(listOf(Images("Fake Album Url")))
val fakeArtistList = listOf(Artist("Fake Artist Id", "Robert"))
val fakeTracksList = TracksList(
    listOf(TrackItem(Track(fakeAlbum, fakeArtistList, "Fake Track Id", "Fake Track Name"), false)))

val fakeImagePLayList = listOf(ImagePlaylist())
val fakePlaylists = listOf(Playlist("Fake Playlist Id", "Fake Playlist Name", fakeImagePLayList))
val fakeFeaturedPlaylists = Playlists(PlaylistsItems(fakePlaylists))
val fakeBooleanList = listOf(true, false, true)