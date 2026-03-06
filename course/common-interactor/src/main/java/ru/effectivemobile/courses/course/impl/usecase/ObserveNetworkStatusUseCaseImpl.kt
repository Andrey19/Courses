package ru.effectivemobile.courses.course.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.usecase.ObserveNetworkStatusUseCase
import ru.effectivemobile.courses.course.api.exchange.NesState
import javax.inject.Inject

internal class ObserveNetworkStatusUseCaseImpl @Inject constructor(
    private val courseRepository: CoursesRepository,
) : ObserveNetworkStatusUseCase {
    override fun invoke(): Flow<NesState> = courseRepository.observeNetworkExchangeStatus()
}