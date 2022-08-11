package com.revature.inviewprep.view.navigation

import com.revature.inviewprep.view.home.controller.HomeController
import com.revature.inviewprep.view.counter.CounterController
import com.revature.inviewprep.view.dagger.hellodagger.controller.DaggerController
import com.revature.inviewprep.view.dagger.repodagger.controller.RepoDaggerController
import com.revature.inviewprep.view.messenger.controller.SendController
import com.revature.inviewprep.view.mosby.mosby_hello.GreetingsController

sealed class NavScreens<T>(val name:String, val controller:Class<T>) {
    companion object{
        val allScreens = listOf(
            HomeScreen,
            CounterScreen,
            GreetingScreen,
            MessengerScreen,
            DaggerScreen,
            RepoDaggerScreen
        )
    }
    object HomeScreen:NavScreens<HomeController>("Home",HomeController::class.java)
    object CounterScreen:NavScreens<CounterController>("Counter",CounterController::class.java)
    object GreetingScreen:NavScreens<GreetingsController>("Greetings",GreetingsController::class.java)
    object MessengerScreen:NavScreens<SendController>("Messenger",SendController::class.java)
    object DaggerScreen:NavScreens<DaggerController>("Hello Dagger", DaggerController::class.java)
    object RepoDaggerScreen:NavScreens<RepoDaggerController>("Dagger Repository",RepoDaggerController::class.java )
}