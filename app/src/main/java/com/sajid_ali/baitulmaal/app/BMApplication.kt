package com.sajid_ali.baitulmaal.app

import android.app.Application
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sajid_ali.baitulmaal.pref.UserPref
import com.sajid_ali.baitulmaal.pref.UserPrefImpl
import javax.inject.Singleton

class BMApplication : Application() {

    @Singleton
    val dataStore = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ), produceFile = { applicationContext.preferencesDataStoreFile("user_data") }
    )

    val userPref: UserPref = UserPrefImpl(dataStore)
}