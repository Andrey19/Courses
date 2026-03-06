package ru.effectivemobile.courses.course.impl.usecase

import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.usecase.ChangeCourseIsLikedUseCase
import javax.inject.Inject

internal class ChangeCourseIsLikedUseCaseImpl @Inject constructor(
    private val courseRepository: CoursesRepository,
) : ChangeCourseIsLikedUseCase {
    override suspend fun invoke(id: Int, hasLike: Boolean) =
        courseRepository.changeCourseIsLiked(id, hasLike)
}