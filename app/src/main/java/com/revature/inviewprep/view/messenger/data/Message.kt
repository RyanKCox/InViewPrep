package com.revature.inviewprep.view.messenger.data

data class Message(
    val userId:Int,
    val time:String,
    val message:String
)
data class User(
    val id:Int,
    val name:String,
    val image:String
)