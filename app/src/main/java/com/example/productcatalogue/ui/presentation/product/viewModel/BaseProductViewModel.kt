package com.example.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.example.productcatalogue.utils.Utils
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseProductViewModel(val repo: ProductRepository) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun validate(
        title: String,
        description: String,
        brand: String,
        category: String,
        price: String,
        stock: String,
        discountPercentage: String,
        rating: String
    ): Boolean {
        if (Utils.validate(
                title,
                description, brand, category, price, stock, discountPercentage, rating
            )
        ) {
            return true
        } else {
            viewModelScope.launch {
                error.emit("")
            }
            return false
        }
    }
}