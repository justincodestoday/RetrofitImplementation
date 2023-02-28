package com.example.productcatelogue.ui.product.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(repo: ProductRepository) : BaseProductViewModel(repo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: String) {

        viewModelScope.launch {
            Log.d("ewqewq","ewqewqewq")
            val res = repo.getProductById(id)
            res.let {
                product.value = it
            }
        }
    }

    fun updateProduct(
        id: String,
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

    class Provider(private val repository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditProductViewModel(repository) as T
        }
    }

}