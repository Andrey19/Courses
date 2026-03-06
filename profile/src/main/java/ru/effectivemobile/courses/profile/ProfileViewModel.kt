package ru.effectivemobile.courses.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.courses.base_feature.mvvm.BaseViewModel
import ru.effectivemobile.courses.base_feature.navigation.NavigationManager
import ru.effectivemobile.courses.base_feature.navigation.destinations.EntryFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.RoutesInfo
import ru.effectivemobile.courses.interactor.session.usecase.LogOutUseCase
import ru.effectivemobile.courses.interactor.session.usecase.ObserveSessionInfoUseCase
import ru.effectivemobile.courses.profile.data.ProfileUiEvent
import ru.effectivemobile.courses.profile.data.ProfileUiState
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val observeSessionInfoUseCase: ObserveSessionInfoUseCase,
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel<ProfileUiState, ProfileUiEvent>() {
    override val _uiState = MutableStateFlow(ProfileUiState())
    override val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    override val _uiEvent = Channel<ProfileUiEvent>()
    override val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeIsLogged()
    }

    private fun observeIsLogged() {
        viewModelScope.launch {
            observeSessionInfoUseCase().collect { isLogged ->
                if (!isLogged) {
                    navigationManager.navigateTo(EntryFragmentRoute(),
                        backStack = RoutesInfo.ENTRY_SCREENPATH)
                }
            }
        }
    }

    fun onSignOutClick() {
        viewModelScope.launch {
            logOutUseCase()
        }
    }

    override fun onBackPressed() {
        navigationManager.navigateBack()
    }
}