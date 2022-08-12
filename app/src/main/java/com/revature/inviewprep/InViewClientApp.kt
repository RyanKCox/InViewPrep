package com.revature.inviewprep

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.bluelinelabs.conductor.Controller
import com.ivianuu.contributer.conductor.HasControllerInjector
import com.revature.inviewprep.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber
import javax.inject.Inject

open class InViewClientApp :Application() {

//    val appComponent:AppComponent by lazy {
//        DaggerAppComponent.create()
//    }
    companion object{
        fun get(@NonNull context: Context):InViewClientApp{
            return context.applicationContext as InViewClientApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        //setupApplicationComponent()
    }

    /*private fun setupApplicationComponent() {
        val appComponent:AppComponent = prepareApplicationComponent().build()
        appComponent.inject(this)
    }
    @NonNull protected fun prepareApplicationComponent():AppComponent.Builder{
        return DaggerAppComponent.builder().app(this)
    }*/
}