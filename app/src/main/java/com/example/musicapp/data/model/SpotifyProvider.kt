package com.example.musicapp.data.model


import com.example.musicapp.data.model.spotifymodels.Playlists
import com.example.musicapp.data.model.spotifymodels.TracksList
import javax.inject.Inject
import javax.inject.Singleton

//se crea como singleton para evitar que se creen varias instancias del provider
@Singleton
class SpotifyProvider @Inject constructor() {
    //Aqui va el repositorio Room
    //recuperaremos de internet el listado completo y nuestro "repositorio" almacenará la respuesta
    var playlist: TracksList? = null
    var savedPlaylist: TracksList? = null
    var featuredPlayList: Playlists? = null
}