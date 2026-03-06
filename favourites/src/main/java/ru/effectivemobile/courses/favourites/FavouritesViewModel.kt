package ru.effectivemobile.courses.favourites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.effectivemobile.courses.base_feature.mvvm.BaseViewModel
import ru.effectivemobile.courses.course.adapter.CoursesItem
import ru.effectivemobile.courses.course.api.usecase.ChangeCourseIsLikedUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveLikedCoursesUseCase
import ru.effectivemobile.courses.favourites.data.FavouritesUiEvent
import ru.effectivemobile.courses.favourites.data.FavouritesUiState
import ru.effectivemobile.courses.favourites.mapper.FavouriteCourseMapper
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    private val observeLikedCoursesUseCase: ObserveLikedCoursesUseCase,
    private val changeCourseIsLikedUseCase: ChangeCourseIsLikedUseCase,
    private val favouriteCourseMapper: FavouriteCourseMapper,
): BaseViewModel<FavouritesUiState, FavouritesUiEvent>() {
    override val _uiState = MutableStateFlow(FavouritesUiState())
    override val uiState: StateFlow<FavouritesUiState> = _uiState.asStateFlow()

    override val _uiEvent = Channel<FavouritesUiEvent>()
    override val uiEvent = _uiEvent.receiveAsFlow()

    init {
        subscribeOnCourses()
    }

    private fun subscribeOnCourses() {
        viewModelScope.launch {
            observeLikedCoursesUseCase().collect { data ->
                _uiState.update {
                    val favouriteCoursesData = favouriteCourseMapper(data)
                    it.copy(
                        courses = favouriteCoursesData
                    )
                }
            }
        }
    }

    fun onCoursesFavouriteBtnClick(course: CoursesItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                changeCourseIsLikedUseCase(course.id, !course.hasLike)
            }
        }
    }
}
