package com.revature.inviewprep.view.messenger.presenter

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.home.model.ChatRepository
import com.revature.inviewprep.view.messenger.controller.MessengerView
import com.revature.inviewprep.view.messenger.controller.SendController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ReceivePresenter(
    private val router:Router,
    private val chatRepository:ChatRepository
    ) :MviBasePresenter<MessengerView,MessengerViewState>(){

    private val chat = chatRepository.getChat()

    override fun bindIntents() {

        val sendClick = intent { it.sendMessageIntent() }
            .switchMap {
                if(it.message.isNotEmpty()) {
                    ChatRepository.addMessage(it)
                    Observable.just(MessengerViewState.DisplayMessages(chat.value?.toList()!!))
                }
                else{
                    Observable.just(MessengerViewState.MessageEmpty)
                }
            }
            .doOnNext{
                router.pushController(
                    RouterTransaction.with(SendController())
                        .pushChangeHandler(FadeChangeHandler())
                        .popChangeHandler(FadeChangeHandler()))
                router.popController(router.backstack[router.backstackSize-2].controller)

            }
            .ofType(MessengerViewState::class.java)

        val data = Observable
            .just(MessengerViewState.DisplayMessages(chat.value?.toList()!!))
            .delay(2,TimeUnit.SECONDS)
            .doOnSubscribe { MessengerViewState.Loading }
            .ofType(MessengerViewState::class.java)

        val viewState = data.mergeWith(sendClick)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state->view.render(state)}
    }

}