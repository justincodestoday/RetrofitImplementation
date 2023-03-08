package com.justin.productcatalog.ui.presentation.credential.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.User
import com.justin.productcatalog.data.service.AuthService
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import com.justin.productcatalog.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authService: AuthService) :
    BaseViewModel() {
//    val user: MutableLiveData<User> = MutableLiveData()
    val navigate: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        if (Utils.validate(name, email, password, confirmPassword) && password == confirmPassword) {
            viewModelScope.launch {
                try {
                    val res = safeApiCall { authService.createUser(User(name, email, password)) }
                    if (res != null) {
                        navigate.emit(Unit)
                    } else {
                        error.emit("Register Failed")
                    }
                } catch (e: Exception) {
                    error.emit(e.message.toString())
                }
            }
        } else {
            viewModelScope.launch {
                error.emit("Please fill in all the fields.")
            }
        }
    }
}