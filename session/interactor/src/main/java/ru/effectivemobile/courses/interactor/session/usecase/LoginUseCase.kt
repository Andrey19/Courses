package ru.effectivemobile.courses.interactor.session.usecase

fun interface LogInUseCase {
    suspend operator fun invoke(email: String, password: String)
}