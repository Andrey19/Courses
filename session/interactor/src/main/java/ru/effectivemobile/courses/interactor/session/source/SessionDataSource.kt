package ru.effectivemobile.courses.interactor.session.source

import kotlinx.coroutines.flow.Flow

internal interface SessionDataSource {
    fun observeIsLogged(): Flow<Boolean>
    suspend fun saveUserData(email: String, password: String)
    suspend fun logout()
}