package com.example.productcatalogue.ui.presentation.product.viewModel

import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseProductViewModel(val repo: ProductRepository) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
}