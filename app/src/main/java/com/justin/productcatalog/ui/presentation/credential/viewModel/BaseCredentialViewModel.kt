package com.justin.productcatalog.ui.presentation.credential.viewModel

import com.justin.productcatalog.data.service.AuthService
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseCredentialViewModel(val authService: AuthService) : BaseViewModel() {
    val navigate: MutableSharedFlow<Unit> = MutableSharedFlow()
}