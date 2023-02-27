package com.fantasy.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.utils.Utils
import kotlinx.coroutines.launch

class UpdateProductViewModel(repo: ProductRepository): BaseProductViewModel(repo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = repo.getProductsById(id)
            res.let {
                product.value = it
            }
        }
    }

    fun updateProduct(
        id: Int,
        product: Product
    ) {
        val validationStatus = Utils.validate(
            product.brand,
            product.category,
            product.title,
            product.description,
            product.price.toString(),
            product.discountPercentage.toString(),
            product.rating.toString(),
            product.stock.toString(),
        )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall { repo.updateProduct(id, product) }
                finish.emit(Unit)
            } else {
                error.emit("Please fill in every detail")
            }
        }
    }
    class Provider(private val repo: ProductRepository): ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return UpdateProductViewModel(repo) as T
        }
    }
}