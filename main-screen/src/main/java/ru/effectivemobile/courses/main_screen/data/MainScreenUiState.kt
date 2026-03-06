package ru.effectivemobile.courses.main_screen.data

import ru.effectivemobile.courses.base_feature.mvvm.State
import ru.effectivemobile.courses.course.adapter.CoursesItem
import ru.effectivemobile.courses.main_screen.mapper.SortType

internal data class MainScreenUiState(
    val sortType: SortType = SortType.ORIGINAL,
    val courses: List<CoursesItem> = listOf(),
) : State {
    val nextSortType: SortType
        get() = if(sortType == SortType.ORIGINAL) SortType.PUBLISH_DATE else SortType.ORIGINAL
}