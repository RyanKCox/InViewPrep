package com.revature.inviewprep.view.messenger.di

import com.bluelinelabs.conductor.Router
import com.revature.inviewprep.view.messenger.controller.ReceiveController
import com.revature.inviewprep.view.messenger.controller.SendController
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface MessengerComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create():MessengerComponent
    }
    fun inject(sendController: SendController)
    fun inject(receiveController: ReceiveController)
}