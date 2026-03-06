package ru.effectivemobile.courses.favourites.data

import ru.effectivemobile.courses.base_feature.mvvm.State
import ru.effectivemobile.courses.course.adapter.CoursesItem

internal data class FavouritesUiState(
    val courses: List<CoursesItem> = listOf(),
) : State