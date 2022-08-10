package com.revature.inviewprep.view.messenger.data

import androidx.lifecycle.MutableLiveData

object ChatRepository {
    private val chat = MutableLiveData<MutableList<Message>>(mutableListOf())

    fun getChat() = chat
    fun addMessage(msg:Message){
        chat.value?.add(msg)
    }
}