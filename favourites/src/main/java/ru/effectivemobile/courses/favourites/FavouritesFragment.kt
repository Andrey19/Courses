package ru.effectivemobile.courses.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.effectivemobile.courses.base_feature.mvvm.BaseFragment
import ru.effectivemobile.courses.course.adapter.createCoursesAdapter
import ru.effectivemobile.courses.favourites.data.FavouritesUiEvent
import ru.effectivemobile.courses.favourites.data.FavouritesUiState
import ru.effectivemobile.courses.favourites.databinding.FavouritesFragmentBinding

@ExperimentalCoroutinesApi
@AndroidEntryPoint
internal class FavouritesFragment : BaseFragment<FavouritesUiState, FavouritesUiEvent>() {
    lateinit var binding: FavouritesFragmentBinding

    override val viewModel: FavouritesViewModel by viewModels()

    private val allCoursesAdapter by lazy {
        createCoursesAdapter(
            onCoursesFavouriteButtonItemClick = viewModel::onCoursesFavouriteBtnClick
        )
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
        setupRecycler()
    }

    override fun render(state: FavouritesUiState) {
        allCoursesAdapter.items = state.courses
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecycler() {
        with(binding.list) {
            adapter = allCoursesAdapter
        }
    }

}