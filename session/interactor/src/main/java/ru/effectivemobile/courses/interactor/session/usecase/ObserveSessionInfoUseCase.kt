package ru.effectivemobile.courses.interactor.session.usecase

import kotlinx.coroutines.flow.Flow

fun interface ObserveSessionInfoUseCase {
    operator fun invoke(): Flow<Boolean>
}