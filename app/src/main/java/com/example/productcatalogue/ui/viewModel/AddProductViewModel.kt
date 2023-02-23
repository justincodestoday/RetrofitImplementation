package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productcatalogue.data.repository.ProductRepository

class AddProductViewModel(private val repo: ProductRepository) : BaseViewModel() {

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}