package com.example.productcatelogue.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.User
import com.example.productcatelogue.data.service.AuthService
import com.example.productcatelogue.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepo: AuthService): BaseViewModel() {
    val finish:MutableSharedFlow<Unit> = MutableSharedFlow()
    fun signUp(name:String,email:String,pass:String,confirmPass:String){
        if(Utils.validate(name,email,pass,confirmPass)&&pass==confirmPass){
            viewModelScope.launch {
                safeApiCall { authRepo.createUser(User(name,email,pass)) }
                finish.emit(Unit)
            }
        }else{
            viewModelScope.launch {
                error.emit("please provide all the information")
            }
        }
    }
}