package ru.effectivemobile.courses.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.effectivemobile.courses.base_feature.mvvm.BaseFragment
import ru.effectivemobile.courses.course.adapter.createCoursesAdapter
import ru.effectivemobile.courses.main_screen.data.MainScreenUiEvent
import ru.effectivemobile.courses.main_screen.data.MainScreenUiState
import ru.effectivemobile.courses.main_screen.data.MoveToFirstItemEvent
import ru.effectivemobile.courses.main_screen.data.NetworkErrorEvent
import ru.effectivemobile.courses.main_screen.databinding.MainScreenFragmentBinding
import ru.effectivemobile.courses.uikit.R
@ExperimentalCoroutinesApi
@AndroidEntryPoint
internal class MainScreenFragment : BaseFragment<MainScreenUiState, MainScreenUiEvent>() {
    lateinit var binding: MainScreenFragmentBinding

    override val viewModel: MainScreenViewModel by viewModels()

    private val allCoursesAdapter by lazy {
        createCoursesAdapter (
            onCoursesFavouriteButtonItemClick = viewModel::onCoursesFavouriteBtnClick
        )
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
        setupRecycler()
        initListeners()
    }

    override fun render(state: MainScreenUiState) {
        allCoursesAdapter.items = state.courses
    }

    override fun handleUiEvent(event: MainScreenUiEvent) {
        when (event) {
            MoveToFirstItemEvent -> {
                binding.list.scrollToPosition(0)
            }

            NetworkErrorEvent -> Toast.makeText(
                requireContext(),
                getText(R.string.network_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListeners() {
        with(binding) {
            sortButton.setOnClickListener {
                viewModel.onSortTypeClick()
            }
        }
    }

    private fun setupRecycler() {
        with(binding.list) {
            adapter = allCoursesAdapter
        }
    }

}