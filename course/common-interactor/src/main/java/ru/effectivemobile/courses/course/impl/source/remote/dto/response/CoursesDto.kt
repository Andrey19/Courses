package ru.effectivemobile.courses.course.impl.source.remote.dto.response

import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.transport.Transformable

class CoursesDto(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val publishDate: String,
    val hasLike: Boolean,
) : Transformable<Courses> {
    override fun transform(): Courses {
        return Courses(
            id = id,
            title = title,
            text = text,
            price = price,
            rate = rate,
            publishDate = publishDate,
            startDate = startDate,
            hasLike = hasLike
        )
    }
}