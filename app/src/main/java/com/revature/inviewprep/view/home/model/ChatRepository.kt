package com.revature.inviewprep.view.home.model

import androidx.lifecycle.MutableLiveData
import com.revature.inviewprep.view.messenger.data.Message

object ChatRepository {
    private val chat = MutableLiveData<MutableList<Message>>(mutableListOf())

    fun getChat() = chat
    fun addMessage(msg:Message){
        chat.value?.add(msg)
    }
}