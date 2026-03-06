package ru.effectivemobile.courses.interactor.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.interactor.session.repository.SessionRepository
import ru.effectivemobile.courses.interactor.session.usecase.ObserveSessionInfoUseCase
import javax.inject.Inject

internal class ObserveSessionInfoUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : ObserveSessionInfoUseCase {
    override fun invoke(): Flow<Boolean> =
        sessionRepository.observeIsLogged()
}