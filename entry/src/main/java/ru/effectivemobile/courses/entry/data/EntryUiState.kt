package ru.effectivemobile.courses.entry.data

import ru.effectivemobile.courses.base_feature.mvvm.State

data class EntryUiState(
    val email: String = "",
    val isEmailCorrect: Boolean = false,
    val password: String = "",
    val isPasswordCorrect: Boolean = false,
) : State {
    val isInputCorrect: Boolean
        get() {
            return isEmailCorrect && isPasswordCorrect
        }
}