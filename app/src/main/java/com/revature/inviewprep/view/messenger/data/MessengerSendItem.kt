package com.revature.inviewprep.view.messenger.data

import android.view.View
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.MessengerReceivedItemBinding
import com.revature.inviewprep.databinding.MessengerSentItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class MessengerSendItem(private val message: Message)
    :BindableItem<MessengerSentItemBinding>(){
    override fun bind(viewBinding: MessengerSentItemBinding, position: Int) {
        viewBinding.textTimestampSent.text = message.getTimeSent()
        viewBinding.textMessageSent.text = message.message
        viewBinding.textDateSent.text = message.getDateSent()

    }

    override fun getLayout() = R.layout.messenger_sent_item

    override fun initializeViewBinding(view: View) = MessengerSentItemBinding.bind(view)
}
class MessengerReceiveItem(private val message: Message)
    :BindableItem<MessengerReceivedItemBinding>(){
    override fun bind(viewBinding: MessengerReceivedItemBinding, position: Int) {
        viewBinding.textTimestampReceive.text = message.getTimeSent()
        viewBinding.textMessageReceive.text = message.message
        viewBinding.textDateReceive.text = message.getDateSent()
        viewBinding.imageProfileReceive.setImageResource(message.user.imageId)
        viewBinding.textNameReceive.text = message.user.name
    }

    override fun getLayout() = R.layout.messenger_received_item

    override fun initializeViewBinding(view: View) = MessengerReceivedItemBinding.bind(view)

}