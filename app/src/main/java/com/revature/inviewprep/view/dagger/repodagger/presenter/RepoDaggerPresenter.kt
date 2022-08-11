package com.revature.inviewprep.view.dagger.repodagger.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.dagger.repodagger.controller.RepoDaggerVS
import com.revature.inviewprep.view.dagger.repodagger.controller.RepoDaggerView
import com.revature.inviewprep.view.dagger.repodagger.data.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepoDaggerPresenter @Inject constructor(
    val userRepository: UserRepository
    ) :MviBasePresenter<RepoDaggerView,RepoDaggerVS>(){

    override fun bindIntents() {


        val data = Observable
            .just(RepoDaggerVS.Display(userRepository.getName()))
            .delay(1,TimeUnit.SECONDS)
            .doOnSubscribe { RepoDaggerVS.Loading }
            .ofType(RepoDaggerVS::class.java)

        val viewState = data
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state->view.render(state)}
    }

}