package com.revature.inviewprep.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CounterVM:ViewModel() {
    private val _state = MutableStateFlow<CounterState>(CounterState.ResetState())
    val state : StateFlow<CounterState>
        get() = _state
    val userIntent = Channel<CounterIntent>(Channel.UNLIMITED)

    private var _counter = 0
    val counter = _counter

    init {
        handleIntent()
    }

    fun increaseCount(){
        ++_counter
        _state.value = CounterState.CountingState(_counter)
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it){
                    is CounterIntent.IncreaseCount -> increaseCount()
                    is CounterIntent.ResetCount -> resetCount()
                }
            }
        }
    }

    private fun resetCount() {
        _counter = 0
        _state.value = CounterState.ResetState(_counter)
    }
}