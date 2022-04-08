package com.revature.expiration_date.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsManager(private  val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

        val NOTIFICATION_THREE_DAY_KEY = booleanPreferencesKey("notification_three_day")
        val NOTIFICATION_ONE_DAY_KEY = booleanPreferencesKey("notification_one_day")
        val NOTIFICATION_ZERO_DAY_KEY = booleanPreferencesKey("notification_zero_day")
        val NOTIFICATION_AFTER_DAY_KEY = booleanPreferencesKey("notification_after_day")
    }

    val getThreeDay: Flow<Boolean> = context.dataStore.data.map {
        it[NOTIFICATION_THREE_DAY_KEY] ?: false
    }
    val getOneDay: Flow<Boolean> = context.dataStore.data.map {
        it[NOTIFICATION_ONE_DAY_KEY] ?: false
    }
    val getZeroDay: Flow<Boolean> = context.dataStore.data.map {
        it[NOTIFICATION_ZERO_DAY_KEY] ?: true
    }
    val getAfterDay: Flow<Boolean> = context.dataStore.data.map {
        it[NOTIFICATION_AFTER_DAY_KEY] ?: false
    }

    suspend fun saveThreeDay(isSet: Boolean) {
        context.dataStore.edit {
            it[NOTIFICATION_THREE_DAY_KEY] = isSet
        }
    }

    suspend fun saveOneDay(isSet: Boolean) {
        context.dataStore.edit {
            it[NOTIFICATION_ONE_DAY_KEY] = isSet
        }
    }

    suspend fun saveZeroDay(isSet: Boolean) {
        context.dataStore.edit {
            it[NOTIFICATION_ZERO_DAY_KEY] = isSet
        }
    }

    suspend fun saveAfterDay(isSet: Boolean) {
        context.dataStore.edit {
            it[NOTIFICATION_AFTER_DAY_KEY] = isSet
        }
    }
}