package com.sajid_ali.baitulmaal.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPrefImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPref {
    override fun isUserLogin(): Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[USER_LOGIN_KEY] ?: false
        }
    }

    override suspend fun saveIsUserLogin(isUserLogin: Boolean) {
        dataStore.edit {
            it[USER_LOGIN_KEY] = isUserLogin
        }
    }
}