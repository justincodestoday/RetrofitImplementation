package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: ProductRepository) : ViewModel() {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = repo.getProductById(id)
            res.let {
                product.value = it
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repo) as T
        }
    }
}