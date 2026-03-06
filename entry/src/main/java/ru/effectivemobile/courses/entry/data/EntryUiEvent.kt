package ru.effectivemobile.courses.entry.data

import ru.effectivemobile.courses.base_feature.mvvm.UiEvent

sealed class EntryUiEvent : UiEvent {
    data object SetInputButtonActive : EntryUiEvent()
    data object SetInputButtonInActive : EntryUiEvent()
}