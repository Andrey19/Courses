package ru.effectivemobile.courses.course.impl.source.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.effectivemobile.courses.course.impl.source.remote.CoursesUrls.All_COURSES
import ru.effectivemobile.courses.course.impl.source.remote.dto.response.CoursesResponse

interface CoursesApiService {
    @GET(All_COURSES)
    suspend fun getAll(): Response<CoursesResponse>
}