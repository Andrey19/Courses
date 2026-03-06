package ru.effectivemobile.courses.course.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.usecase.ObserveCoursesUseCase
import ru.effectivemobile.courses.domain.course.Courses
import javax.inject.Inject

internal class ObserveCoursesUseCaseImpl @Inject constructor(
    private val courseRepository: CoursesRepository,
) : ObserveCoursesUseCase {
    override fun invoke(): Flow<List<Courses>> = courseRepository.observeCourses()
}