package com.revature.inviewprep.view.counter

import androidx.constraintlayout.motion.utils.ViewState

sealed class CounterState:ViewState() {
    object Loading:CounterState()
    data class CountingState(val count:Int):CounterState()
    data class ResetState(val count:Int = 0):CounterState()
}