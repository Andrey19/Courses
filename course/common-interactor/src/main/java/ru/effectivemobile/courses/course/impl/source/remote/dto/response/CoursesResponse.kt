package ru.effectivemobile.courses.course.impl.source.remote.dto.response

import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.transport.Transformable

class CoursesResponse(
    val courses: List<CoursesDto>
) : Transformable<List<Courses>> {
    override fun transform(): List<Courses> =
        courses.map { it.transform() }
}