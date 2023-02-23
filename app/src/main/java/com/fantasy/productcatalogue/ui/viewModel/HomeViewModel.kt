package com.fantasy.productcatalogue.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: ProductRepository) : BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
//    val error: MutableSharedFlow<String> = MutableSharedFlow()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
//            try {
                val res = safeApiCall { repo.getAllProducts() }
                products.value = res?.toMutableList()
//                products.value = mutableListOf(res)
//            } catch (e: Exception) {
//                error.emit(e.message.toString())
//                Log.d("debugging", e.message.toString())
//            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}