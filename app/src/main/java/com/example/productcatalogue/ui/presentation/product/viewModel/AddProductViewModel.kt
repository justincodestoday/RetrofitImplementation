package com.example.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.utils.Utils.validate
import kotlinx.coroutines.launch

class AddProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {
    fun addProduct(
        product: Product
    ) {
        val validationStatus = validate(
            product.title,
            product.description,
            product.brand,
            product.category,
            product.price.toString(),
            product.stock.toString(),
            product.discountPercentage.toString(),
            product.rating.toString()
        )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall { repo.addProduct(product) }
                finish.emit(Unit)
            } else {
                error.emit("Kindly provide all information")
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}