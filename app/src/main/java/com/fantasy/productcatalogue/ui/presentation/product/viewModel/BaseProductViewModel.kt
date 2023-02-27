package com.fantasy.productcatalogue.ui.presentation.product.viewModel

import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseProductViewModel(val repo: ProductRepository): BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
}