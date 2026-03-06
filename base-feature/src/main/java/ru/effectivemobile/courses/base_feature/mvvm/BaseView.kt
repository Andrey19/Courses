package ru.effectivemobile.courses.base_feature.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface BaseView<ScreenState : State, ScreenEvent : UiEvent> : BaseStateView<ScreenState, ScreenEvent>,
    CoroutineScope {

    val viewModel: BaseViewModel<ScreenState, ScreenEvent>

    fun setUiStateFlow(): Flow<ScreenState> =
        viewModel.uiState

    fun setUiEventFlow(): Flow<ScreenEvent> =
        viewModel.uiEvent

    fun setUiStateFlowCollect() {
        launch {
            setUiStateFlow().collect(::render)
        }
    }

    fun setUiEventFlowCollect() {
        launch {
            setUiEventFlow().collect(::handleUiEvent)
        }
    }
}
