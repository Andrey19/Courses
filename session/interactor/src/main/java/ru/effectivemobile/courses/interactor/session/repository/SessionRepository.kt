package ru.effectivemobile.courses.interactor.session.repository

import kotlinx.coroutines.flow.Flow

internal interface SessionRepository {
    fun observeIsLogged(): Flow<Boolean>
    suspend fun setLoggedIn(email: String, password: String)
    suspend fun logout()
}