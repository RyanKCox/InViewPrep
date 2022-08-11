package com.revature.inviewprep.view.dagger.repodagger.data

interface UserRepository {
    fun getName():String
}
class UserDataRepository(private val baseURL:String):UserRepository {
    override fun getName(): String {
        return "SampleName, baseURL = $baseURL"
    }
}