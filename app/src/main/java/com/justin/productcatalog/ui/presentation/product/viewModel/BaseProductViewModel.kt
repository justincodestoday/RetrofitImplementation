package com.justin.productcatalog.ui.presentation.product.viewModel

import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseProductViewModel(val productRepo: ProductRepository): BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
}