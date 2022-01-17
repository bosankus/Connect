package com.bosankus.stream.connect

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.offline.ChatDomain
import javax.inject.Inject

@HiltAndroidApp
class ConnectApplication: Application() {

    @Inject lateinit var client: ChatClient

    override fun onCreate() {
        super.onCreate()
        ChatDomain.Builder(client, applicationContext).build()
    }
}