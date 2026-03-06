package ru.effectivemobile.courses.entry

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.effectivemobile.courses.base_feature.mvvm.BaseViewModel
import ru.effectivemobile.courses.base_feature.navigation.ExternalUrlRoute
import ru.effectivemobile.courses.base_feature.navigation.NavigationManager
import ru.effectivemobile.courses.base_feature.navigation.destinations.MainScreenFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.RoutesInfo
import ru.effectivemobile.courses.entry.data.EntryUiEvent
import ru.effectivemobile.courses.entry.data.EntryUiState
import ru.effectivemobile.courses.interactor.session.usecase.LogInUseCase
import ru.effectivemobile.courses.interactor.session.usecase.ObserveSessionInfoUseCase
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val logInUseCase: LogInUseCase,
    private val observeSessionInfoUseCase: ObserveSessionInfoUseCase
) : BaseViewModel<EntryUiState, EntryUiEvent>() {

    override val _uiState = MutableStateFlow(EntryUiState())
    override val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    override val _uiEvent = Channel<EntryUiEvent>()
    override val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeIsLogged()
    }

    override fun onBackPressed() {
        navigationManager.navigateBack()
    }

    fun onSignInClick() {
        viewModelScope.launch {
            with(uiState.value) {
                logInUseCase(email, password)
                navigationManager.navigateTo(
                    MainScreenFragmentRoute(),
                    backStack = RoutesInfo.MAIN_SCREENPATH
                )
            }
        }
    }

    fun onEmailInput(email: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    email = email,
                    isEmailCorrect = isEmailValid(email)
                )
            }
            setInputButtonVisibility(uiState.value.isInputCorrect)
        }
    }

    fun onPasswordInput(password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    password = password,
                    isPasswordCorrect = isPasswordValid(password)
                )
            }
            setInputButtonVisibility(uiState.value.isInputCorrect)
        }
    }

    fun onVkClick() {
        navigationManager.navigateTo(ExternalUrlRoute("https://vk.com"))
    }

    fun onOkClick() {
        navigationManager.navigateTo(ExternalUrlRoute("https://ok.ru"))
    }

    fun setInputButtonVisibility(isInputCorrect: Boolean) {
        if (isInputCorrect) {
            sendEvent(EntryUiEvent.SetInputButtonActive)
        } else {
            sendEvent(EntryUiEvent.SetInputButtonInActive)
        }
    }

    private fun observeIsLogged() {
        viewModelScope.launch {
            observeSessionInfoUseCase().collect { isLogged ->
                if (isLogged) {
                    navigationManager.navigateTo(
                        MainScreenFragmentRoute(),
                        backStack = RoutesInfo.MAIN_SCREENPATH
                    )
                }
            }
        }
    }

    private fun isEmailValid(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS
            .matcher(value)
            .matches()
    }

    private fun isPasswordValid(value: String): Boolean {
        return value.isNotEmpty()
    }
}
