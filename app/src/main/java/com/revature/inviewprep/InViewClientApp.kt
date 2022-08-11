package com.revature.inviewprep

import android.app.Application
import android.content.Context
import com.bluelinelabs.conductor.Controller
import com.ivianuu.contributer.conductor.HasControllerInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class InViewClientApp :Application(),HasControllerInjector {
    companion object{
        fun get(context: Context): InViewClientApp{
            return context.applicationContext as InViewClientApp
        }
    }

    @Inject
    lateinit var controllerInjector : DispatchingAndroidInjector<Controller>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }

    override fun controllerInjector() = controllerInjector
}