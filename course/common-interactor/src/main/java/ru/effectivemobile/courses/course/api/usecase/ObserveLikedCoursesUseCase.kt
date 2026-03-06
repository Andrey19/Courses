package ru.effectivemobile.courses.course.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.domain.course.Courses

fun interface ObserveLikedCoursesUseCase {
    operator fun invoke(): Flow<List<Courses>>
}