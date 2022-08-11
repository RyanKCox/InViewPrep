package com.revature.inviewprep.view.dagger.hellodagger.di

import com.revature.inviewprep.view.dagger.hellodagger.presenter.DaggerPresenter
import dagger.Component
import javax.inject.Inject

@Component
interface MagicBoxComponent{
    fun poke(app: DaggerPresenter)
}
class Info @Inject constructor(){
    val text = "Hello Dagger"
}