package com.revature.inviewprep.view.dagger.hellodagger.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.dagger.hellodagger.controller.DaggerView
import com.revature.inviewprep.view.dagger.hellodagger.controller.DaggerViewState
import com.revature.inviewprep.view.dagger.hellodagger.di.DaggerMagicBoxComponent
import com.revature.inviewprep.view.dagger.hellodagger.di.Info
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DaggerPresenter :MviBasePresenter<DaggerView, DaggerViewState>(){

    @Inject
    lateinit var info: Info

    override fun bindIntents() {
        DaggerMagicBoxComponent.create().poke(this)

        val data = Observable
            .just(DaggerViewState.DisplayScreen(info.text))
            .delay(1,TimeUnit.SECONDS)
            .doOnSubscribe { DaggerViewState.Loading }
            .ofType(DaggerViewState::class.java)

        val viewState = data.observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state->view.render(state)}
    }

}