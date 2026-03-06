package ru.effectivemobile.courses.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.effectivemobile.courses.base_feature.mvvm.BaseViewModel
import ru.effectivemobile.courses.base_feature.navigation.NavigationManager
import ru.effectivemobile.courses.base_feature.navigation.destinations.EntryFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.FavouritesFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.MainScreenFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.ProfileFragmentRoute
import ru.effectivemobile.courses.base_feature.navigation.destinations.RoutesInfo
import ru.effectivemobile.courses.interactor.session.usecase.ObserveSessionInfoUseCase
import ru.effectivemobile.courses.main.data.MainUiEvent
import ru.effectivemobile.courses.main.data.MainUiState
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeSessionInfoUseCase: ObserveSessionInfoUseCase,
    private val navigationManager: NavigationManager,
) : BaseViewModel<MainUiState, MainUiEvent>() {

    override val _uiState = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    override val _uiEvent = Channel<MainUiEvent>()
    override val uiEvent = _uiEvent.receiveAsFlow()

    init {
        observeNavManagerState()
        observeIsLogged()
    }

    override fun onBackPressed() {
        navigationManager.navigateBack()
    }

    fun onMenuItemClicked(itemId: Int) = when (itemId) {
        R.id.action_main_screen -> {
            navigationManager.navigateTo(
                MainScreenFragmentRoute(),
                backStack = RoutesInfo.MAIN_SCREENPATH
            )
            true
        }

        R.id.action_favourites -> {
            navigationManager.navigateTo(
                FavouritesFragmentRoute(),
                backStack = RoutesInfo.FAVOURITES_SCREENPATH
            )
            true
        }

        R.id.action_profile -> {
            navigationManager.navigateTo(
                ProfileFragmentRoute(),
                backStack = RoutesInfo.PROFILE_SCREENPATH
            )
            true
        }

        else -> false
    }

    private fun observeNavManagerState() {
        viewModelScope.launch {
            navigationManager.nmState.collect { navigationRoute ->
                when (navigationRoute.lastScreen) {
                    RoutesInfo.MAIN_SCREENPATH ->
                        sendEvent(MainUiEvent.SetNavBarItem(R.id.action_main_screen))
                    RoutesInfo.PROFILE_SCREENPATH ->
                        sendEvent(MainUiEvent.SetNavBarItem(R.id.action_profile))
                    RoutesInfo.FAVOURITES_SCREENPATH ->
                        sendEvent(MainUiEvent.SetNavBarItem(R.id.action_favourites))
                }
            }
        }
    }

    private fun observeIsLogged() {
        viewModelScope.launch {
            observeSessionInfoUseCase().collect { isLogged ->
                _uiState.update {
                    it.copy(
                        isLogged = isLogged
                    )
                }
                if (!isLogged) {
                    sendEvent(MainUiEvent.SetBottomNavigationInvisible)
                    navigationManager.navigateTo(EntryFragmentRoute(),
                        backStack = RoutesInfo.ENTRY_SCREENPATH)
                } else {
                    sendEvent(MainUiEvent.SetBottomNavigationVisible)
                    navigationManager.navigateTo(MainScreenFragmentRoute(),
                        backStack = RoutesInfo.MAIN_SCREENPATH)
                }

            }
        }
    }
}
