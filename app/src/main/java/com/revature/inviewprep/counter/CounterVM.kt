package com.revature.inviewprep.counter

import androidx.lifecycle.ViewModel

class CounterVM:ViewModel() {

    private var _counter = 0
    val counter:Int
        get() = _counter

    fun increaseCount(){
        _counter++
    }
}