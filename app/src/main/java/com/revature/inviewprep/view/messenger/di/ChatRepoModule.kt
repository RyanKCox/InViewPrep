package com.revature.inviewprep.view.messenger.di

import com.revature.inviewprep.view.messenger.data.ChatDataRepository
import com.revature.inviewprep.view.messenger.data.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ChatRepoModule {
    @Singleton
    @Binds
    abstract fun provideChatRepo(chatRepository: ChatDataRepository): ChatRepository
}