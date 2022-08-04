package com.revature.inviewprep.view.counter

sealed class CounterIntent {
    object IncreaseCount:CounterIntent()
    object ResetCount:CounterIntent()
}