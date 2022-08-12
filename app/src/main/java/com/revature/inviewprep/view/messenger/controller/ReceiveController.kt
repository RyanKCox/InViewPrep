package com.revature.inviewprep.view.messenger.controller

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby3.MviController
import com.ivianuu.contributer.conductor.ConductorInjection
import com.jakewharton.rxbinding2.view.clicks
import com.revature.inviewprep.InViewClientApp
import com.revature.inviewprep.MainActivity
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ControllerMessengerReceiveBinding
import com.revature.inviewprep.view.messenger.data.*
import com.revature.inviewprep.view.messenger.di.MessengerRouterModule
import com.revature.inviewprep.view.messenger.presenter.MessengerViewState
import com.revature.inviewprep.view.messenger.presenter.ReceivePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class ReceiveController :MviController<MessengerView,ReceivePresenter>(),MessengerView{

    private val user = User("James")
    @Inject
    lateinit var chatRepository: ChatRepository

    private lateinit var sendButton:ImageButton
    private lateinit var sendMessage:EditText
    private lateinit var recycler:RecyclerView
    private lateinit var progressBar: ProgressBar
    private val adapter:GroupAdapter<GroupieViewHolder> = GroupAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): View {
        val view = inflater.inflate(R.layout.controller_messenger_receive,container,false)

        setup(view)

        return view
    }

    private fun setup(view:View){
        val bind = ControllerMessengerReceiveBinding.bind(view)
        sendButton = bind.imageReceive
        sendMessage = bind.textSendmessageReceive
        recycler = bind.recyclerMessengerReceive
        progressBar = bind.progressBarMessengerReceive

        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

//        chatRepository = ChatDataRepository()
        ConductorInjection.inject(this)
//        (view.context as MainActivity).app.appComponent.inject(this)
//        (view.context.applicationContext as InViewClientApp).appComponent.inject(this)
//        DaggerMessengerComponent.builder()
//            .messengerRouterModule(MessengerRouterModule((router)))
//            .build()
//            .inject(this)

//        DaggerMessengerComponent.builder().build().inject(this)
    }

    override fun createPresenter() = ReceivePresenter(router, chatRepository)

    override fun sendMessageIntent(): Observable<Message> = sendButton.clicks().map {

        Message(
            user,
            Calendar.getInstance().timeInMillis,
            sendMessage.text.toString())
    }

    override fun render(viewState: MessengerViewState) {
        when(viewState){
            is MessengerViewState.DisplayMessages->{
                adapter.clear()
                adapter.addAll(viewState.messageList.map {
                    if(it.user == user)
                        MessengerSendItem(it)
                    else
                        MessengerReceiveItem(it)
                })
                renderDisplay()
            }
            is MessengerViewState.Loading->{
                renderLoading()
            }
            is MessengerViewState.MessageEmpty->{
                renderMessageEmpty()
            }
        }
    }
    private fun renderLoading(){
        recycler.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        sendMessage.visibility = View.GONE
        sendButton.visibility = View.GONE

    }
    private fun renderMessageEmpty(){
        sendMessage.hint = "Message Empty!"
        sendMessage.setHintTextColor(Color.RED)

    }
    private fun renderDisplay(){
        recycler.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        sendMessage.visibility = View.VISIBLE
        sendButton.visibility = View.VISIBLE
        sendMessage.hint = "Your Message Here!"
        sendMessage.setHintTextColor(Color.GRAY)
        recycler.layoutManager?.let {
            it.scrollToPosition(it.itemCount-1)
        }

    }

}