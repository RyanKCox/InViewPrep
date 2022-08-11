package com.revature.inviewprep.view.dagger.repodagger.di

import com.revature.inviewprep.view.dagger.repodagger.controller.RepoDaggerController
import com.revature.inviewprep.view.dagger.repodagger.presenter.RepoDaggerPresenter
import dagger.Component

@Component(modules = [RepoDaggerModule::class])
interface RepoDaggerComponent {
    fun inject(controller: RepoDaggerController)
}