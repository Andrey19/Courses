package ru.effectivemobile.courses.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.effectivemobile.courses.base_feature.navigation.NavigationManager
import ru.effectivemobile.courses.base_feature.navigation.destinations.RoutesInfo
import ru.effectivemobile.courses.main.data.MainUiEvent
import ru.effectivemobile.courses.main.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNav: BottomNavigationView

    private val backPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    viewModel.onBackPressed()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav = binding.bottomNavigation

        navigationManager.setFragmentManager(supportFragmentManager)
        navigationManager.setNavigationContainer(binding.navHostFragment.id)
        navigationManager.setExcludedBackStack(setOf(RoutesInfo.ENTRY_SCREENPATH))
        onBackPressedDispatcher.addCallback(this, backPressedCallback)

        initViews()
        setUiEventFlowCollect()
    }

    override fun onDestroy() {
        backPressedCallback.remove()
        super.onDestroy()
    }

    private fun initViews() {
        initInput()
        applySystemBottomInsets()
    }

    private fun initInput() = bottomNav.setOnItemSelectedListener {
        viewModel.onMenuItemClicked(it.itemId)
    }

    private fun applySystemBottomInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun setUiEventFlowCollect() {
        lifecycleScope.launch {
            viewModel
                .uiEvent
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleUiEvent(it)
                }
        }
    }

    private fun handleUiEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.SetNavBarItem -> {
                if (bottomNav.selectedItemId != event.itemId) {
                    bottomNav.selectedItemId = event.itemId
                }
            }
            MainUiEvent.SetBottomNavigationVisible -> {
                bottomNav.visibility = View.VISIBLE
            }

            MainUiEvent.SetBottomNavigationInvisible -> {
                bottomNav.visibility = View.INVISIBLE
            }

        }
    }
}
