package com.revature.inviewprep.view.messenger.data

import android.view.View
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.MessengerSentItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class MessengerItem(private val message: Message) :BindableItem<MessengerSentItemBinding>(){
    override fun bind(viewBinding: MessengerSentItemBinding, position: Int) {
        viewBinding.textTimestampSent.text = message.time
        viewBinding.textMessageSent.text = message.message
        viewBinding.textDateSent.text = "Placeholder"

    }

    override fun getLayout() = R.layout.messenger_sent_item

    override fun initializeViewBinding(view: View) = MessengerSentItemBinding.bind(view)

}