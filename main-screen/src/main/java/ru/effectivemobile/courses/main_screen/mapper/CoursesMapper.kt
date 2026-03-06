package ru.effectivemobile.courses.main_screen.mapper

import ru.effectivemobile.courses.base.resources.StringResources
import ru.effectivemobile.courses.course.adapter.CoursesItem
import ru.effectivemobile.courses.domain.course.Courses
import javax.inject.Inject

enum class SortType {
    ORIGINAL,
    PUBLISH_DATE
}

internal class CoursesMapper @Inject constructor(
    private val stringResources: StringResources
) : (SortType, List<Courses>) -> List<CoursesItem> {

    override fun invoke(
        sortType: SortType,
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
        }.sortedByDescending {
            when (sortType) {
                SortType.ORIGINAL -> it.id.toString()
                SortType.PUBLISH_DATE -> it.publishDate
            }
        }
    }
}

internal class CoursesItemMapper @Inject constructor() :
        (SortType, List<CoursesItem>) -> List<CoursesItem> {

    override fun invoke(
        sortType: SortType,
        data: List<CoursesItem>,
    ): List<CoursesItem> {
        return data.sortedByDescending {
            when (sortType) {
                SortType.ORIGINAL -> it.id.toString()
                SortType.PUBLISH_DATE -> it.publishDate
            }
        }
    }
}