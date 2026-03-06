package ru.effectivemobile.courses.profile.data

import ru.effectivemobile.courses.base_feature.mvvm.State

internal data class ProfileUiState(
    val isLogged: Boolean = false
) : State