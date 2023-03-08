package com.justin.productcatalog.ui.presentation.product.viewModel

import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import com.justin.productcatalog.util.Utils
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseProductViewModel(val productRepo: ProductRepository) : BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun validate(
        brand: String,
        category: String,
        title: String,
        description: String,
        price: String,
        discount: String,
        rating: String,
        stock: String
    ): Boolean {
        if (Utils.validate(brand, category, title, description, price, discount, rating, stock)) {
            return true
        }
        viewModelScope.launch {
            error.emit("Please fill in all the fields.")
        }
        return false
    }
}