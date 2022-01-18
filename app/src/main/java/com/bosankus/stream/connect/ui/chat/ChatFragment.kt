package com.bosankus.stream.connect.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.bosankus.stream.connect.databinding.FragmentChatBinding
import com.bosankus.stream.connect.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BindingFragment<FragmentChatBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChatBinding::inflate

    private val args: ChatFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
    }


}