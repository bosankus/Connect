package com.bosankus.stream.connect.ui.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bosankus.stream.connect.databinding.FragmentChannelBinding
import com.bosankus.stream.connect.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.core.internal.InternalStreamChatApi
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

@AndroidEntryPoint
class ChannelFragment : BindingFragment<FragmentChannelBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChannelBinding::inflate

    private val viewModel: ChannelViewModel by activityViewModels()

    @OptIn(InternalStreamChatApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the logged in user
        val user = viewModel.getUser()
        if (user == null) {
            findNavController().popBackStack()
            return
        }

        val factory = ChannelListViewModelFactory(
            filter = Filters.eq("type", "messaging"),
            sort = ChannelListViewModel.DEFAULT_SORT,
            limit = 15
        )

        val channelListViewModel: ChannelListViewModel by viewModels { factory }
        val channelListHeaderViewModel: ChannelListHeaderViewModel by viewModels()

        channelListViewModel.bindView(binding.channelListView, viewLifecycleOwner)
        channelListHeaderViewModel.bindView(binding.channelListHeaderView, viewLifecycleOwner)

        binding.channelListHeaderView.setOnUserAvatarLongClickListener {
            viewModel.logout()
            findNavController().popBackStack()
        }
    }
}