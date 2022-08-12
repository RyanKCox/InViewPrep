package com.revature.inviewprep.view.messenger.presenter

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.revature.inviewprep.view.messenger.data.ChatRepository
import com.revature.inviewprep.view.messenger.controller.MessengerView
import com.revature.inviewprep.view.messenger.controller.ReceiveController
import com.revature.inviewprep.view.messenger.data.Message
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SendPresenter @Inject constructor(
    private val router: Router,
    private val chatRepository: ChatRepository
    ) : MviBasePresenter<MessengerView,MessengerViewState>() {


    override fun bindIntents() {

        val sendIntent = intent { it.sendMessageIntent() }
            .switchMap {
                if(it.message.isNotEmpty()) {
                    chatRepository.addMessage(it)
                    Observable.just(MessengerViewState.DisplayMessages(chatRepository.chat.toList()))
                }
                else
                    Observable.just(MessengerViewState.MessageEmpty)
            }
            .doOnNext{
                router.pushController(
                    RouterTransaction.with(ReceiveController())
                        .pushChangeHandler(FadeChangeHandler())
                        .popChangeHandler(FadeChangeHandler()))
                router.popController(router.backstack[router.backstackSize-2].controller())


            }
            .ofType(MessengerViewState::class.java)

        val data = Observable
            .just(MessengerViewState.DisplayMessages(chatRepository.chat.toList()))
            .delay(1,TimeUnit.SECONDS)
            .doOnSubscribe{
                MessengerViewState.Loading
            }
            .ofType(MessengerViewState::class.java)

        val viewState = data
            .mergeWith(sendIntent)
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