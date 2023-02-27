package com.example.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.utils.Utils
import kotlinx.coroutines.launch

class EditProductViewModel(repo:ProductRepository):BaseProductViewModel(repo) {

    val product: MutableLiveData<Product> = MutableLiveData()
    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = repo.getProductById(id)
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

    class Provider (private val repository: ProductRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditProductViewModel(repository) as T
        }

    }
}