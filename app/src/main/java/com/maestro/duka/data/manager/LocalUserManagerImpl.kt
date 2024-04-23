package com.maestro.duka.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.maestro.duka.domain.manager.LocalUserManager
import com.maestro.duka.utils.Constants
import com.maestro.duka.utils.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl (
    private val context:Context
):LocalUserManager{
    override suspend fun saveAppEntry() {
        context.datastore.edit {
            it[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {
            it[PreferenceKeys.APP_ENTRY] ?:false
        }
    }

    override suspend fun saveToken(token: String) {
        context.datastore.edit {
            it[PreferenceKeys.TOKEN] = token
        }
    }

    override fun readToken(): Flow<String> {
        return context.datastore.data.map {
            it[PreferenceKeys.TOKEN]?:""
        }
    }

}

private val Context.datastore :DataStore<Preferences> by preferencesDataStore(name=USER_SETTING)
private object PreferenceKeys{
    val APP_ENTRY = booleanPreferencesKey(Constants.APPENTRY)
    val TOKEN = stringPreferencesKey(Constants.TOKEN)
}