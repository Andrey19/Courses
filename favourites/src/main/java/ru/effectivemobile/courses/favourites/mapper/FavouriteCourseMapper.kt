package ru.effectivemobile.courses.favourites.mapper

import ru.effectivemobile.courses.base.resources.StringResources
import ru.effectivemobile.courses.course.adapter.CoursesItem
import ru.effectivemobile.courses.domain.course.Courses
import javax.inject.Inject

internal class FavouriteCourseMapper @Inject constructor(
    private val stringResources: StringResources
) : (List<Courses>) -> List<CoursesItem> {

    override fun invoke(
        data: List<Courses>,
    ): List<CoursesItem> {
        return data.map {
            CoursesItem(
                id = it.id,
                title = it.title,
                text = it.text,
                price = stringResources.getPriceFormatted(it.price),
                rate = it.rate,
                startDate = stringResources.getDateFormatted(it.startDate),
                hasLike = it.hasLike,
                publishDate = it.publishDate
            )
        }
    }
}