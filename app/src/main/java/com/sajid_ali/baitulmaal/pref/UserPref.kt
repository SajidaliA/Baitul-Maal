package com.sajid_ali.baitulmaal.pref

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_LOGIN_KEY = booleanPreferencesKey("user_login")

interface UserPref {
    fun isUserLogin(): Flow<Boolean>

    suspend fun saveIsUserLogin(isUserLogin: Boolean)
}