package com.fantasy.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.ui.viewModel.BaseViewModel
import com.fantasy.productcatalogue.utils.Utils.validate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AddProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {
    fun addProduct(
        prod: Product
    ) {
        val validationStatus = validate(
            prod.title, prod.description, prod.price.toString(), prod.rating.toString(), prod.stock.toString(), prod.brand, prod.category, prod.discountPercentage.toString()
        )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall { repo.addProduct(prod)}
                finish.emit(Unit)
            } else {
                error.emit("I'm done")
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}