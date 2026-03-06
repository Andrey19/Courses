package ru.effectivemobile.courses.main_screen.data

import ru.effectivemobile.courses.base_feature.mvvm.UiEvent

sealed class MainScreenUiEvent : UiEvent

data object MoveToFirstItemEvent : MainScreenUiEvent()

data object NetworkErrorEvent : MainScreenUiEvent()