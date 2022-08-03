package com.revature.inviewprep.counter

sealed class CounterIntent {
    object IncreaseCount:CounterIntent()
    object ResetCount:CounterIntent()
}