package com.example.musicapp.core

import android.content.Context
import android.content.SharedPreferences


private const val PREF_NAME = "token-preference"
private const val SESSION_DATA_TOKEN = "token"

fun saveSession(context: Context, token: String) {
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(SESSION_DATA_TOKEN, token)
    editor.apply()
}

fun getSavedSession(context: Context): String {
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    return sharedPref.getString(SESSION_DATA_TOKEN, "").toString()
}


