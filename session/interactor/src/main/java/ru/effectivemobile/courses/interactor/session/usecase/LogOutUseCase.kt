package ru.effectivemobile.courses.interactor.session.usecase

fun interface LogOutUseCase {
    suspend operator fun invoke()
}