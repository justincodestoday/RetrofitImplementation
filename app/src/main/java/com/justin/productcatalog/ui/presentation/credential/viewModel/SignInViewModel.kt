package com.justin.productcatalog.ui.presentation.credential.viewModel

import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.service.AuthService
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authService: AuthService) :
    BaseViewModel() {
    val navigate: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = safeApiCall { authService.login(email, password) }
                if (res == true) {
                    navigate.emit(Unit)
                } else {
                    error.emit("Login Failed")
                }
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }
}