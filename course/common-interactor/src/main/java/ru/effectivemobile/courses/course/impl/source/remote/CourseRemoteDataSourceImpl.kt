package ru.effectivemobile.courses.course.impl.source.remote

import retrofit2.Response
import ru.effectivemobile.courses.course.api.source.CourseRemoteDataSource
import ru.effectivemobile.courses.course.impl.source.remote.dto.response.CoursesResponse
import javax.inject.Inject

internal class CourseRemoteDataSourceImpl @Inject constructor(
    private val courseApi: CoursesApiService
) : CourseRemoteDataSource {

    override suspend fun getCourses(): Response<CoursesResponse> =
        courseApi.getAll()
}