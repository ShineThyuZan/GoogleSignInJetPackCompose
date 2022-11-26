package com.omgea.googlesigninsay.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(value = null)
    val user: StateFlow<User?> = _user

    suspend fun signIn(email: String, displayName: String, profileUrl: String) {
        delay(1000)
        _user.value = User(email, displayName, profileUrl)
    }
}