package ru.effectivemobile.courses.course.api.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.course.api.exchange.NesState

internal interface CoursesRepository {

    val _nesState: MutableStateFlow<NesState>

    val nesState: StateFlow<NesState>
    suspend fun clearCourses()

    fun observeCourses(): Flow<List<Courses>>

    fun observeNetworkExchangeStatus(): Flow<NesState>

    fun observeLikedCourses(): Flow<List<Courses>>

    suspend fun changeCourseIsLiked(id: Int, hasLike: Boolean)
}
