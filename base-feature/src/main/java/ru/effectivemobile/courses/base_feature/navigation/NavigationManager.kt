package ru.effectivemobile.courses.base_feature.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.effectivemobile.courses.base_feature.navigation.FragmentNavigationConstants.MAIN_BACKSTACK
import ru.effectivemobile.courses.base_feature.navigation.data.NavManagerState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private val _nmState = MutableStateFlow(NavManagerState())
    val nmState: StateFlow<NavManagerState> = _nmState.asStateFlow()

    private var excludedBackStack = emptySet<String>()

    fun setExcludedBackStack(excluded: Set<String>) {
        excludedBackStack = excluded
    }

    private var fragmentManager: FragmentManager? = null

    fun setFragmentManager(manager: FragmentManager) {
        this.fragmentManager = manager
    }

    private var defaultContainer: Int = 0

    fun setNavigationContainer(containerId: Int) {
        defaultContainer = containerId
    }

    fun navigateTo(
        route: NavigationRoute,
        container: Int = defaultContainer,
        backStack: String = MAIN_BACKSTACK,
        args: Bundle? = null
    ) {
        when (route) {
            is FragmentRoute<*> -> navigateToFragment(route, container, backStack, args)
            is ExternalUrlRoute -> openUrl(route.url)
        }
    }

    private fun navigateToFragment(
        route: FragmentRoute<out Fragment>,
        container: Int = defaultContainer,
        backStack: String = MAIN_BACKSTACK,
        args: Bundle? = null
    ) {
        if (nmState.value.lastScreen != backStack) {
            val fragment = route.getScreenClass()?.java?.newInstance()?.apply {
                arguments = args
            } ?: throw IllegalArgumentException("Fragment class not found for route: $route")

            fragmentManager?.beginTransaction()
                ?.replace(container, fragment)
                ?.apply { addToBackStack(backStack) }
                ?.commit()

            _nmState.update {
                it.copy(
                    lastScreen = backStack
                )
            }
        }
    }

    private fun openUrl(url: String) {
        val context = fragmentManager?.fragments?.firstOrNull()?.context
            ?: throw IllegalStateException("No valid context available for opening URL")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun navigateBack() {
        val size = fragmentManager?.backStackEntryCount ?: 0
        if (size > 1) {
            val backFragment = fragmentManager!!.getBackStackEntryAt(size - 2).name ?: ""

            if (!excludedBackStack.contains(backFragment)) {
                fragmentManager?.popBackStack()
                _nmState.update {
                    it.copy(
                        lastScreen = backFragment
                    )
                }
            }
        }
    }
}