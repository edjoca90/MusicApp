package com.example.musicapp.core

import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

object SpotifyHelper {
    //se crea una funcion para obtener una instancia de AuthorizationRequest lista para usar
    fun getAuthorizationRequest(
        type: AuthorizationResponse.Type,
        clientId: String,
        redirectUri: String,
        scopes: Array<String>,
    ): AuthorizationRequest {
        return AuthorizationRequest
            .Builder(clientId, type, redirectUri)
            .setScopes(scopes)
            .build()
    }
}