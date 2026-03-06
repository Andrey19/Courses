package ru.effectivemobile.courses.course.api.source

import kotlinx.coroutines.flow.Flow
import ru.effectivemobile.courses.domain.course.Courses

interface CourseLocalDataSource {

    suspend fun clearCourses()
    suspend fun saveCourses(courses: List<Courses>)

    suspend fun changeCourseIsLiked(id: Int, hasLike: Boolean)

    fun observeCourses(): Flow<List<Courses>>

    fun observeLikedCourses(): Flow<List<Courses>>
}