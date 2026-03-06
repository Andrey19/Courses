package ru.effectivemobile.courses.course.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.usecase.ObserveLikedCoursesUseCase
import ru.effectivemobile.courses.domain.course.Courses
import javax.inject.Inject

internal class ObserveLikedCoursesUseCaseImpl @Inject constructor(
    private val courseRepository: CoursesRepository,
) : ObserveLikedCoursesUseCase {
    override fun invoke(): Flow<List<Courses>> = courseRepository.observeLikedCourses()
}