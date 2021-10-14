package br.com.felipefaustini.core.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {

    private const val PREFS_NAME = "mesa_news_prefs"
    private const val TOKEN_PARAM = "tokenParam"

    private lateinit var sharedPreferences: SharedPreferences

    fun Application.setupPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        sharedPreferences
            .edit()
            .putString(TOKEN_PARAM, token)
            .apply()
    }

    fun getToken(): String {
        return sharedPreferences
            .getString(TOKEN_PARAM, "") ?: ""
    }

    fun clearPreferences() {
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }

}