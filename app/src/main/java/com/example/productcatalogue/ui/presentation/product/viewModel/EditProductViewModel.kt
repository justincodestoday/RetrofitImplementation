package com.example.productcatalogue.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.utils.Utils.validate
import kotlinx.coroutines.launch

class EditProductViewModel(repo: ProductRepository) : BaseProductViewModel(repo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun editProduct(
        id: Int,
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
                safeApiCall { repo.editProduct(id, product) }
                finish.emit(Unit)
            } else {
                error.emit("Kindly provide all information")
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditProductViewModel(repo) as T
        }
    }
}