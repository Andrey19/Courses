package ru.effectivemobile.courses.interactor.impl.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.effectivemobile.courses.interactor.session.source.SessionDataSource
import ru.effectivemobile.courses.interactor.impl.source.SessionKeys.sessionDataStore
import javax.inject.Inject

object SessionKeys {
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val EMAIL = stringPreferencesKey("email")
    val PASSWORD = stringPreferencesKey("password")

    val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "session_prefs"
    )
}

internal class SessionDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SessionDataSource {

    private val dataStore = context.sessionDataStore

    override fun observeIsLogged(): Flow<Boolean> =
        dataStore.data
            .map { prefs ->

                prefs[SessionKeys.IS_LOGGED_IN] ?: false
            }
            .distinctUntilChanged()

    override suspend fun saveUserData(email: String, password: String) {
        dataStore.edit { prefs ->
            prefs[SessionKeys.IS_LOGGED_IN] = true
            prefs[SessionKeys.EMAIL] = email
            prefs[SessionKeys.PASSWORD] = password
        }
    }

    override suspend fun logout() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

}