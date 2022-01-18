package com.bosankus.stream.connect.ui.channel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(private val client: ChatClient): ViewModel() {

    fun getUser(): User? {
        return client.getCurrentUser()
    }

    fun logout() {
        client.disconnect()
    }

}