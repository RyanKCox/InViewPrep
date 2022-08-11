package com.revature.inviewprep.view.dagger.repodagger.di

import com.revature.inviewprep.view.dagger.repodagger.data.UserDataRepository
import com.revature.inviewprep.view.dagger.repodagger.data.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepoDaggerModule(private val baseURL:String){
    @Provides
    fun provideUserRepository():UserRepository{
        return UserDataRepository(baseURL)
    }
}