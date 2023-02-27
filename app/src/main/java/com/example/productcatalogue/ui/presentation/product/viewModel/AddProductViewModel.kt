package com.example.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.example.productcatalogue.utils.Utils.validate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AddProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {

    fun addProduct(
        prod: Product
    ) {
        val validationStatus = validate(
            prod.title, prod.description, prod.price.toString(),
            prod.discountPercentage.toString(), prod.rating.toString(),
            prod.stock.toString(), prod.brand, prod.category
        )
        viewModelScope.launch {
            if (validationStatus) {

                safeApiCall { repo.addProduct(prod) }
                finish.emit(Unit)

            } else {

                error.emit("Please fill in every details")
            }
        }
    }

    class Provider(val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}