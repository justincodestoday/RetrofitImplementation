package com.justin.productcatalog.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.util.Utils
import kotlinx.coroutines.launch

class UpdateProductViewModel(productRepo: ProductRepository) : BaseProductViewModel(productRepo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = productRepo.getProductById(id)
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
                safeApiCall { productRepo.updateProduct(id, product) }
                finish.emit(Unit)
            } else {
                error.emit("Please fill in every detail")
            }
        }
    }

    class Provider(private val productRepo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UpdateProductViewModel(productRepo) as T
        }
    }
}