package ru.effectivemobile.courses.base_feature.mvvm

interface BaseStateView<ScreenState : State, ScreenEvent : UiEvent> {

    fun initViews(){}

    fun render(state: ScreenState){}

    fun handleUiEvent(event: ScreenEvent){}
}