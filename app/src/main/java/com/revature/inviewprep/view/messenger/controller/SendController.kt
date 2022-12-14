package com.revature.inviewprep.view.messenger.controller

import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jakewharton.rxbinding2.view.clicks
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ControllerMessengerSendBinding
import com.revature.inviewprep.view.core.MviBaseController
import com.revature.inviewprep.view.messenger.data.*
import com.revature.inviewprep.view.messenger.presenter.SendPresenter
import com.revature.inviewprep.view.messenger.presenter.MessengerViewState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable
import java.util.*

class SendController : MviBaseController<MessengerView,SendPresenter>(),MessengerView {

    private val user = User("Ryan")

    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private lateinit var recycler:RecyclerView
    private lateinit var sendButton:ImageButton
    private lateinit var sendMessage:EditText
    private lateinit var progressBar:ProgressBar

    @LayoutRes
    override fun getLayoutId() = R.layout.controller_messenger_send

    override fun onViewBound(view: View) {
        super.onViewBound(view)
        setup(view)
    }
    private fun setup(view:View){
        recycler = ControllerMessengerSendBinding.bind(view).recyclerMessengerSend
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter
        sendButton = ControllerMessengerSendBinding.bind(view).imageSend
        sendMessage = ControllerMessengerSendBinding.bind(view).textSendmessageSend
        progressBar = ControllerMessengerSendBinding.bind(view).progressBarMessengerSend
    }

    override fun sendMessageIntent(): Observable<Message> = sendButton.clicks().map {

        val message = Message(user, Calendar.getInstance().timeInMillis, sendMessage.text.toString())
        sendMessage.text.clear()
        message
    }

    override fun render(viewState: MessengerViewState) {
        when (viewState){
            is MessengerViewState.DisplayMessages->{
                adapter.clear()
                adapter.addAll(viewState.messageList.map {
                    if(it.user == user)
                        MessengerSendItem(it)
                    else
                        MessengerReceiveItem(it)
                })
                renderDisplayScreen()
            }
            is MessengerViewState.MessageEmpty->{
                renderEmptyMessage()
            }
            is MessengerViewState.Loading->{
                renderLoading()

            }
        }
    }
    private fun renderDisplayScreen(){
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
    private fun renderLoading(){
        recycler.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        sendMessage.visibility = View.GONE
        sendButton.visibility = View.GONE
    }
    private fun renderEmptyMessage(){
        sendMessage.hint = "Message Empty!"
        sendMessage.setHintTextColor(Color.RED)

    }


}

interface MessengerView:MvpView{
    fun sendMessageIntent():Observable<Message>
    fun render(viewState: MessengerViewState)
}