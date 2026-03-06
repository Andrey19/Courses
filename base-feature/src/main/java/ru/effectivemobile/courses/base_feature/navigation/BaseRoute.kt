package ru.effectivemobile.courses.base_feature.navigation

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

sealed class NavigationRoute

abstract class FragmentRoute<S : Fragment>(private val classPath: String) : NavigationRoute() {
    fun getScreenClass(): KClass<out S>? {
        return kotlin.runCatching {
            Class.forName(classPath).kotlin
        }.onFailure {
            if (it is ClassNotFoundException) {
                throw it
            } else {
                error("Unknown error for class: ${this::class.java.name}")
            }
        }.getOrNull() as? KClass<out S>
    }
}

class ExternalUrlRoute(val url: String) : NavigationRoute()