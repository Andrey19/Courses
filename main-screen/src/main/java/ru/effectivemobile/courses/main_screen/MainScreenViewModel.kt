package ru.effectivemobile.courses.main_screen

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
import ru.effectivemobile.courses.course.api.exchange.NetworkExchangeStatus
import ru.effectivemobile.courses.course.api.usecase.ChangeCourseIsLikedUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveCoursesUseCase
import ru.effectivemobile.courses.course.api.usecase.ObserveNetworkStatusUseCase
import ru.effectivemobile.courses.main_screen.data.MainScreenUiEvent
import ru.effectivemobile.courses.main_screen.data.MainScreenUiState
import ru.effectivemobile.courses.main_screen.data.MoveToFirstItemEvent
import ru.effectivemobile.courses.main_screen.data.NetworkErrorEvent
import ru.effectivemobile.courses.main_screen.mapper.CoursesItemMapper
import ru.effectivemobile.courses.main_screen.mapper.CoursesMapper
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val observeCoursesUseCase: ObserveCoursesUseCase,
    private val observeNetworkStatusUseCase: ObserveNetworkStatusUseCase,
    private val changeCourseIsLikedUseCase: ChangeCourseIsLikedUseCase,
    private val coursesMapper: CoursesMapper,
    private val coursesItemMapper: CoursesItemMapper,
): BaseViewModel<MainScreenUiState, MainScreenUiEvent>() {
    override val _uiState = MutableStateFlow(MainScreenUiState())
    override val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    override val _uiEvent = Channel<MainScreenUiEvent>()
    override val uiEvent = _uiEvent.receiveAsFlow()

    init {
        subscribeOnNetworkStatus()
        subscribeOnCourses()
    }

    private fun subscribeOnNetworkStatus() {
        viewModelScope.launch {
            observeNetworkStatusUseCase().collect { data ->
                if (data.networkExchangeStatus != NetworkExchangeStatus.SUCCESSFUL) {
                    sendEvent(NetworkErrorEvent)
                }
            }

        }
    }

    private fun subscribeOnCourses() {
        viewModelScope.launch {
            observeCoursesUseCase().collect { data ->
                _uiState.update {
                    val coursesData = coursesMapper(uiState.value.sortType, data)
                    it.copy(
                        courses = coursesData
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

    fun onSortTypeClick() {
        viewModelScope.launch {
            _uiState.update {
                val coursesData =
                    coursesItemMapper(uiState.value.nextSortType, uiState.value.courses)
                it.copy(
                    courses = coursesData,
                    sortType = uiState.value.nextSortType
                )
            }
            sendEvent(MoveToFirstItemEvent)
        }
    }

}
