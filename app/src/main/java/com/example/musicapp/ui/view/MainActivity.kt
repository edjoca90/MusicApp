package com.example.musicapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.musicapp.core.SpotifyHelper
import com.example.musicapp.core.saveSession
import com.example.musicapp.data.utils.Constants
import com.example.musicapp.databinding.ActivityMainBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val spotifyAuthorization = SpotifyHelper.getAuthorizationRequest(
        AuthorizationResponse.Type.TOKEN,
        Constants.CLIENT_ID,
        Constants.REDIRECT_URI,
        arrayOf(
            "user-library-read", "user-library-modify"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSpotifyLogin.setOnClickListener {
            getTokenOnClick()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    println(response.accessToken)
                    goToAlbumsNavigationActivity(response.accessToken)
                }
                AuthorizationResponse.Type.ERROR -> {
                    println("Error: You cant obtain a token")
                    Toast.makeText(this, "Error: You cant obtain a token", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    println("Warning: Auth is not Token or Error")
                    Toast.makeText(this, "Warning: Auth is not Token or Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getTokenOnClick() {
        val request: AuthorizationRequest = spotifyAuthorization
        AuthorizationClient.openLoginActivity(this, Constants.REQUEST_CODE, request)
    }

    private fun goToAlbumsNavigationActivity(token: String) {
        saveSession(this, token)
        startActivity(Intent(this, AlbumsActivity::class.java))
    }
}