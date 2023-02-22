package com.example.productcatalogue.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: ProductRepository) : ViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            val res = repo.getAllProducts()
            products.value = res.toMutableList()
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}