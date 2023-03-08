package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.service.AuthService
import com.example.productcatalogue.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthService) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun login(email: String, password: String) {
        if (Utils.validate(
                email, password
            )
        ) {
            viewModelScope.launch {
                safeApiCall { authRepo.login(email, password) }
                finish.emit(Unit)
            }
        } else {
            viewModelScope.launch {
                error.emit("Login failed")
            }
        }
    }
}