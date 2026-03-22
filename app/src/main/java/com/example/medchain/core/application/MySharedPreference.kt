package com.example.medchain.core.application

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MySharedPreference @Inject constructor(
    @ApplicationContext context: Context
) {
    private val PREFERENCE_NAME = "medchain_prefs"
    private val TOKEN_KEY = "auth_token"
    private val sharedPreference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()
    fun saveToken(token: String) {
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }
    fun getToken(): String? {
        return sharedPreference.getString(TOKEN_KEY, null)
    }
    fun clearToken(){
        editor.remove(TOKEN_KEY)
        editor.apply()
    }
}