package com.bosankus.stream.connect.di

import android.content.Context
import com.bosankus.stream.connect.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.getstream.chat.android.client.ChatClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideChatClient(@ApplicationContext context: Context): ChatClient =
        ChatClient.Builder(BuildConfig.API_KEY, context).build()

}