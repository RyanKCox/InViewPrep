package com.revature.inviewprep.view.messenger.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.messenger.controller.MessengerView
import com.revature.inviewprep.view.messenger.data.Message
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers


class SendPresenter : MviBasePresenter<MessengerView,MessengerViewState>() {
    val chat = mutableListOf<Message>()
    override fun bindIntents() {

        val sendIntent = intent { it.sendMessageIntent() }
            .switchMap {
                if(it.message.isNotEmpty()) {
                    chat.add(it)
                    Observable.just(MessengerViewState.DisplayMessages(chat))
                }
                else
                    Observable.just(MessengerViewState.MessageEmpty)
            }
            .ofType(MessengerViewState::class.java)

        val data = Observable
            .just(MessengerViewState.DisplayMessages(chat))
            .ofType(MessengerViewState::class.java)

        val viewState = data.mergeWith(sendIntent)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){ view,state->view.render(state) }
    }

}

sealed class MessengerViewState{
    data class DisplayMessages(
        val messageList:List<Message>
    ) : MessengerViewState()
    object MessageEmpty:MessengerViewState()
    object Loading:MessengerViewState()
}