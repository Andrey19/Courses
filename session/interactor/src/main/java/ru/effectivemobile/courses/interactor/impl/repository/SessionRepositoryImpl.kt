package ru.effectivemobile.courses.interactor.impl.repository

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.interactor.session.repository.SessionRepository
import ru.effectivemobile.courses.interactor.session.source.SessionDataSource
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource,
) : SessionRepository {

    override fun observeIsLogged(): Flow<Boolean> =
        sessionDataSource.observeIsLogged()

    override suspend fun setLoggedIn(email: String, password: String) {
        sessionDataSource.saveUserData(email, password)
    }

    override suspend fun logout() {
        sessionDataSource.logout()
    }

}