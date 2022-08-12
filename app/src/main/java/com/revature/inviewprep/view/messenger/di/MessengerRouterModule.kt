package com.revature.inviewprep.view.messenger.di

import com.bluelinelabs.conductor.Router
import dagger.Module
import dagger.Provides

@Module
class MessengerRouterModule(private val router: Router) {
    @Provides
    fun provideRouter():Router{
        return router
    }
}