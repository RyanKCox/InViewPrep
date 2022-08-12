package com.revature.inviewprep.di

import android.app.Application
import android.content.Context
import android.os.Build
import com.bluelinelabs.conductor.Router
import com.ivianuu.contributer.annotations.AndroidInjectorKeyRegistry
import com.ivianuu.contributer.conductor.ConductorInjectionModule
import com.ivianuu.contributer.conductor.ControllerKey
import com.revature.inviewprep.InViewClientApp
import com.revature.inviewprep.view.messenger.controller.ReceiveController
import com.revature.inviewprep.view.messenger.controller.SendController
import com.revature.inviewprep.view.messenger.di.ChatRepoModule
import com.revature.inviewprep.view.messenger.di.MessengerBindingModule
import com.revature.inviewprep.view.messenger.di.MessengerComponent
import com.revature.inviewprep.view.messenger.di.MessengerRouterModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AndroidInjectorKeyRegistry(keys = [
    ControllerKey::class
])
@Singleton
@Component(modules = [
    ChatRepoModule::class,
    ConductorInjectionModule::class,
//    AppSubComponents::class,
//    MessengerRouterModule::class
])
interface AppComponent{

    fun inject(app:InViewClientApp):InViewClientApp

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun app(app:Application):Builder
//        @BindsInstance
//        fun router(router: Router):Builder

        fun build():AppComponent
    }

    fun messengerComponent():MessengerComponent.Factory
}