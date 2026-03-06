package ru.effectivemobile.courses.course.api.source

import retrofit2.Response
import ru.effectivemobile.courses.course.impl.source.remote.dto.response.CoursesResponse

internal interface CourseRemoteDataSource {

    suspend fun getCourses(): Response<CoursesResponse>
}