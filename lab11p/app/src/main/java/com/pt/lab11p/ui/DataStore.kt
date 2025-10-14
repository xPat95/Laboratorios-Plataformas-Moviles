package com.pt.lab11p.ui

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "app_prefs")

class PrefsDataStore(private val context: Context) {

    private object Keys {
        val NAME = stringPreferencesKey("name")
    }

    val name: Flow<String?> = context.dataStore.data.map { it[Keys.NAME] }

    suspend fun saveName(value: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.NAME] = value
        }
    }

    suspend fun clearName() {
        context.dataStore.edit { prefs ->
            prefs.remove(Keys.NAME)
        }
    }
}