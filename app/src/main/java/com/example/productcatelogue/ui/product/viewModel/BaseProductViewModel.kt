package com.example.productcatelogue.ui.product.viewModel

import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject


abstract class BaseProductViewModel(val repo:ProductRepository):BaseViewModel() {
    val finish:MutableSharedFlow<Unit> = MutableSharedFlow()
}