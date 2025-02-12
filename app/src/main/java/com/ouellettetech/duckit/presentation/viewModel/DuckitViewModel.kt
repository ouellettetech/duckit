package com.ouellettetech.duckit.presentation.viewModel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class DuckitViewModel<State> : ViewModel(), DefaultLifecycleObserver {
    protected  abstract  fun initState(): State

    private val _state = MutableStateFlow(initState())
    val state = _state.asStateFlow()

    protected fun updateState(newState: (currentState: State) -> State) {
        _state.update { state ->
            newState(state)
        }
    }
}