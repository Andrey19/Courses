package ru.effectivemobile.courses.main.data

import ru.effectivemobile.courses.base_feature.mvvm.UiEvent

sealed class MainUiEvent : UiEvent {
    data object SetBottomNavigationVisible : MainUiEvent()
    data object SetBottomNavigationInvisible : MainUiEvent()

    data class SetNavBarItem(val itemId: Int) : MainUiEvent()

}