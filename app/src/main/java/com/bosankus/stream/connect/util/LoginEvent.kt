package com.bosankus.stream.connect.util

sealed class LoginEvent {
    object ErrorInputTooShort: LoginEvent()
    data class ErrorLogin(val message: String): LoginEvent()
    object Success: LoginEvent()
}