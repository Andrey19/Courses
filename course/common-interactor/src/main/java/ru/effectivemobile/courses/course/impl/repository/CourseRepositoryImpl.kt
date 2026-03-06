package ru.effectivemobile.courses.course.impl.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.courses.course.api.exchange.NesState
import ru.effectivemobile.courses.course.api.exchange.NetworkExchangeStatus
import ru.effectivemobile.courses.course.api.repository.CoursesRepository
import ru.effectivemobile.courses.course.api.source.CourseLocalDataSource
import ru.effectivemobile.courses.course.api.source.CourseRemoteDataSource
import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.transport.transform
import javax.inject.Inject

internal class CourseRepositoryImpl @Inject constructor(
    private val coursesLocalDataSource: CourseLocalDataSource,
    private val coursesRemoteDataSource: CourseRemoteDataSource,
) : CoursesRepository {

    override val _nesState = MutableStateFlow(NesState())
    override val nesState: StateFlow<NesState> = _nesState.asStateFlow()

    override suspend fun clearCourses() =
        coursesLocalDataSource.clearCourses()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndSaveCourses()
        }
    }

    override fun observeCourses(): Flow<List<Courses>> =
        coursesLocalDataSource.observeCourses()

    override fun observeNetworkExchangeStatus(): Flow<NesState> = nesState

    override fun observeLikedCourses(): Flow<List<Courses>> =
        coursesLocalDataSource.observeLikedCourses()

    override suspend fun changeCourseIsLiked(id: Int, hasLike: Boolean) =
        coursesLocalDataSource.changeCourseIsLiked(id, hasLike)


    private suspend fun fetchAndSaveCourses() {
        val remoteResult = coursesRemoteDataSource.getCourses()

        if (remoteResult.isSuccessful) {
            val courses = remoteResult.body()?.courses ?: emptyList()
            if (courses.isNotEmpty()) {
                coursesLocalDataSource.clearCourses()
                coursesLocalDataSource.saveCourses(courses.transform())
            }
            _nesState.value = NesState()
        } else {
            _nesState.value = NesState(NetworkExchangeStatus.ERROR)
        }
    }

}