package com.example.productcatelogue.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(private val auth:AuthService):BaseViewModel() {
    val loginFinish:MutableSharedFlow<Unit> = MutableSharedFlow()
    fun login(email:String,pass:String){
        viewModelScope.launch {
            val res = safeApiCall { auth.login(email,pass) }
            if(res!=null){
                loginFinish.emit(Unit)
            }else{
                error.emit("Login Failed")
            }
        }
    }

}