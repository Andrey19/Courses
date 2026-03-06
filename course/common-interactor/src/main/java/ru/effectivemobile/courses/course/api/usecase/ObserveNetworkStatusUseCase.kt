package ru.effectivemobile.courses.course.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.course.api.exchange.NesState

fun interface ObserveNetworkStatusUseCase {
    operator fun invoke(): Flow<NesState>
}