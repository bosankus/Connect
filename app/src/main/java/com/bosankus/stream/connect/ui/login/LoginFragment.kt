package com.bosankus.stream.connect.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bosankus.stream.connect.R
import com.bosankus.stream.connect.databinding.FragmentLoginBinding
import com.bosankus.stream.connect.ui.BindingFragment
import com.bosankus.stream.connect.util.Constants.MIN_USERNAME_LENGTH
import com.bosankus.stream.connect.util.LoginEvent
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.core.internal.exhaustive
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        observeEvents()
    }

    private fun setListeners() {
        binding.apply {
            etUsername.addTextChangedListener { etUsername.error = null }

            btnConfirm.setOnClickListener {
                showConnectingUiState()
                viewModel.connectUser(etUsername.text.toString())
            }
        }
    }

    private fun observeEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect { event ->
                when (event) {
                    is LoginEvent.ErrorLogin -> {
                        showIdleUiState()
                        Toast.makeText(
                            requireContext(),
                            event.message,
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                    is LoginEvent.ErrorInputTooShort -> {
                        showIdleUiState()
                        binding.etUsername.error = resources.getString(
                            R.string.error_username_too_short,
                            MIN_USERNAME_LENGTH
                        )
                    }
                    is LoginEvent.Success -> {
                        showIdleUiState()
                        // navigate to next
                    }
                }
            }
        }
    }

    private fun showConnectingUiState() {
        binding.apply {
            btnConfirm.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showIdleUiState() {
        binding.apply {
            btnConfirm.isVisible = true
            progressBar.isVisible = false
        }
    }

}