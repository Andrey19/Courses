package ru.effectivemobile.courses.interactor.impl.usecase

import ru.effectivemobile.courses.interactor.session.repository.SessionRepository
import ru.effectivemobile.courses.interactor.session.usecase.LogInUseCase
import javax.inject.Inject

internal class LogInUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : LogInUseCase {

    override suspend fun invoke(email: String, password: String) =
        sessionRepository.setLoggedIn(email, password)
}