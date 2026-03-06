package ru.effectivemobile.courses.course.api.usecase

fun interface ChangeCourseIsLikedUseCase {
    suspend operator fun invoke(id: Int, hasLike: Boolean)
}