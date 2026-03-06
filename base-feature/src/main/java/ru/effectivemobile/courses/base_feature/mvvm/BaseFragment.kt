package ru.effectivemobile.courses.base_feature.mvvm

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<ScreenState : State, ScreenEvent : UiEvent> : Fragment(),
    BaseView<ScreenState, ScreenEvent> {

    abstract fun onBackPressed()

    private val backPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUiStateFlowCollect()
        setUiEventFlowCollect()
    }

    override fun setUiStateFlowCollect() {
        launch {
            setUiStateFlow()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::render)
        }
    }

    override fun setUiEventFlowCollect() {
        launch {
            setUiEventFlow()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::handleUiEvent)
        }
    }

    protected open fun setupBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }
}
