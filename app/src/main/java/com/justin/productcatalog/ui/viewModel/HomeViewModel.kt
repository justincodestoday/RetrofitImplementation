package com.justin.productcatalog.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepo: ProductRepository) : ViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val product: MutableLiveData<Product> = MutableLiveData()

    init {
        getProductById(2)
    }

    fun getProducts() {
        viewModelScope.launch {
            val res = productRepo.getAllProducts()
            products.value = res as MutableList<Product>
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            val res = productRepo.getProductById(id)
            res.let {
                product.value = it
                Log.d("debugging", it.toString())
            }
        }
    }

    class Provider(val productRepo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(productRepo) as T
        }
    }
}