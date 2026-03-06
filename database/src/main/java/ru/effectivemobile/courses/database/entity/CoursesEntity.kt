package ru.effectivemobile.courses.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.transport.Transformable

@Entity
data class CoursesEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) : Transformable<Courses> {

    override fun transform(): Courses {
        return Courses(
            id = id,
            title = title,
            text = text,
            price = price,
            rate = rate,
            startDate = startDate,
            publishDate = publishDate,
            hasLike = hasLike
        )
    }
}
