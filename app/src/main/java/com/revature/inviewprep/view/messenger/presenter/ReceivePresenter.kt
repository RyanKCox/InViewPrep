package com.revature.inviewprep.view.messenger.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.messenger.controller.MessengerView
import com.revature.inviewprep.view.messenger.data.Message
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ReceivePresenter :MviBasePresenter<MessengerView,MessengerViewState>(){

    private val chat = mutableListOf<Message>()

    override fun bindIntents() {

        val sendClick = intent { it.sendMessageIntent() }
            .switchMap {
                if(it.message.isNotEmpty()) {
                    chat.add(it)
                    Observable.just(MessengerViewState.DisplayMessages(chat))
                }
                else{
                    Observable.just(MessengerViewState.MessageEmpty)
                }
            }
            .ofType(MessengerViewState::class.java)

        val data = Observable
            .just(MessengerViewState.DisplayMessages(chat))
            .delay(2,TimeUnit.SECONDS)
            .doOnSubscribe { MessengerViewState.Loading }
            .ofType(MessengerViewState::class.java)

        val viewState = data.mergeWith(sendClick)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state->view.render(state)}
    }

}