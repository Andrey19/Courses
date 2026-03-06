package ru.effectivemobile.courses.base_feature.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<ScreenState : State, ScreenEvent : UiEvent> : ViewModel() {
    protected abstract val _uiState: MutableStateFlow<ScreenState>
    abstract val uiState: StateFlow<ScreenState>

    protected open val _uiEvent: Channel<out ScreenEvent> = Channel()
    open val uiEvent: Flow<ScreenEvent> by lazy { _uiEvent.receiveAsFlow() }

    open fun onBackPressed() = Unit

    protected open fun sendEvent(uiEvent: ScreenEvent) {
        viewModelScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            (_uiEvent as Channel<ScreenEvent>).send(uiEvent)
        }
    }
}