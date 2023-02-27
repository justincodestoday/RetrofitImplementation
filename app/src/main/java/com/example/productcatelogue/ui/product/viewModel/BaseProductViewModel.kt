package com.example.productcatelogue.ui.product.viewModel

import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseProductViewModel(val repo:ProductRepository):BaseViewModel() {
    val finish:MutableSharedFlow<Unit> = MutableSharedFlow()
}