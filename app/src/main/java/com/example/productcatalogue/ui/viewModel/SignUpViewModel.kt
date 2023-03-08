package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.User
import com.example.productcatalogue.data.service.AuthService
import com.example.productcatalogue.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepo: AuthService) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {

        if (Utils.validate(
                name,
                email,
                password,
                confirmPassword
            ) && password == confirmPassword
        ) {
            viewModelScope.launch {
                safeApiCall { authRepo.createUser(User(name, email, password)) }
                finish.emit(Unit)
            }
        } else {
            viewModelScope.launch {
                error.emit("Kindly fill in all information")
            }
        }
    }
}