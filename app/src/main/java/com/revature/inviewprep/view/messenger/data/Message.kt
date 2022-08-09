package com.revature.inviewprep.view.messenger.data

import java.text.SimpleDateFormat
import java.util.*


data class Message(
    val user:User,
    val time:Long,
    val message:String
){
    fun getDateSent():String{

        return SimpleDateFormat("MM/dd/yy", Locale.US).format(time)
    }
    fun getTimeSent():String{
        return SimpleDateFormat("hh:mm a",Locale.US).format(time)
    }
}
data class User(
    val name:String,
    val imageId:Int = android.R.drawable.ic_menu_myplaces
)