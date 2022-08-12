package com.revature.inviewprep.view.messenger.data

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatDataRepository @Inject constructor() :ChatRepository {
    override val chat = mutableListOf<Message>()

    override fun addMessage(msg: Message) {
        chat.add(msg)
    }
}

@Singleton
interface ChatRepository{
    val chat:MutableList<Message>
//    fun getChat()
    fun addMessage(msg:Message)
}