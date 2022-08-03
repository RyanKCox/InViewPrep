package com.revature.inviewprep.navigation

import com.revature.inviewprep.home.controller.HomeController
import com.revature.inviewprep.counter.CounterController

sealed class NavScreens<T>(val name:String, val controller:Class<T>) {
    companion object{
        val allScreens = listOf(
            HomeScreen,
            CounterScreen
        )
    }
    object HomeScreen:NavScreens<HomeController>("Home",HomeController::class.java)
    object CounterScreen:NavScreens<CounterController>("Counter",CounterController::class.java)
}