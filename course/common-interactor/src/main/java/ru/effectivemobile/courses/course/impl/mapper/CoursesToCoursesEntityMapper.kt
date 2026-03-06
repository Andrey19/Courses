package ru.effectivemobile.courses.course.impl.mapper

import ru.effectivemobile.courses.base.mapper.Mapper
import ru.effectivemobile.courses.database.entity.CoursesEntity
import ru.effectivemobile.courses.domain.course.Courses
import javax.inject.Inject

class CoursesToCoursesEntityMapper @Inject constructor() : Mapper<Courses, CoursesEntity> {
    override fun map(type: Courses): CoursesEntity {
        with(type) {
            return CoursesEntity(
                id = id,
                title = title,
                publishDate = publishDate,
                text = text,
                price = price,
                rate = rate,
                startDate = startDate,
                hasLike = hasLike
            )
        }
    }
}