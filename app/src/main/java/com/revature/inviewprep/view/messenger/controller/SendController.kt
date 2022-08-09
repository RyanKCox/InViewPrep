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
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jakewharton.rxbinding2.view.clicks
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ControllerMessengerSendBinding
import com.revature.inviewprep.view.messenger.data.Message
import com.revature.inviewprep.view.messenger.data.MessengerItem
import com.revature.inviewprep.view.messenger.presenter.SendPresenter
import com.revature.inviewprep.view.messenger.presenter.MessengerViewState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable

class SendController : MviController<MessengerView,SendPresenter>(),MessengerView {

    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private lateinit var recycler:RecyclerView
    private lateinit var sendButton:ImageButton
    private lateinit var sendMessage:EditText
    private lateinit var progressBar:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_messenger_send,container,false)

        setup(view)

        return view
    }
    private fun setup(view:View){
        recycler = ControllerMessengerSendBinding.bind(view).recyclerMessengerSend
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter
        sendButton = ControllerMessengerSendBinding.bind(view).imageSend
        sendMessage = ControllerMessengerSendBinding.bind(view).textSendmessageSend
        progressBar = ControllerMessengerSendBinding.bind(view).progressBarMessengerSend

    }

    override fun createPresenter() = SendPresenter()
    override fun sendMessageIntent(): Observable<Message> = sendButton.clicks().map {

        val message = Message(0, "10", sendMessage.text.toString())
        sendMessage.text.clear()
        message
    }

    override fun render(viewState: MessengerViewState) {
        when (viewState){
            is MessengerViewState.DisplayMessages->{
                adapter.clear()
                adapter.addAll(viewState.messageList.map { MessengerItem(it) })
                recycler.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                sendMessage.hint = "Your Message Here!"
                sendMessage.setHintTextColor(Color.GRAY)

            }
            is MessengerViewState.MessageEmpty->{
                sendMessage.hint = "Message Empty!"
                sendMessage.setHintTextColor(Color.RED)
            }
            is MessengerViewState.Loading->{
                recycler.visibility = View.GONE
                progressBar.visibility = View.VISIBLE


            }
        }
    }

}

interface MessengerView:MvpView{
    fun sendMessageIntent():Observable<Message>
    fun render(viewState: MessengerViewState)
}