package ru.effectivemobile.courses.interactor.impl.usecase

import ru.effectivemobile.courses.interactor.session.repository.SessionRepository
import ru.effectivemobile.courses.interactor.session.usecase.LogOutUseCase
import javax.inject.Inject

internal class LogOutUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : LogOutUseCase {

    override suspend fun invoke() {
        sessionRepository.logout()
    }

}