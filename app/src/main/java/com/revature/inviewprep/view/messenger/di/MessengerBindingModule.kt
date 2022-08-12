package com.revature.inviewprep.view.messenger.di

import com.revature.inviewprep.view.messenger.controller.ReceiveController
import com.revature.inviewprep.view.messenger.controller.SendController
import dagger.Module

@Module
abstract class MessengerBindingModule {
    abstract fun inject(sendController: SendController)
    abstract fun inject(receiveController: ReceiveController)
}
/*
@Module
abstract class MessengerSendModule {
    abstract fun inject(sendController: SendController)
}*/
