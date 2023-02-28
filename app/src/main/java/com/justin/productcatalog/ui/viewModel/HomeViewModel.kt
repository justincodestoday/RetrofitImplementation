package com.justin.productcatalog.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepo: ProductRepository) :
    BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val product: MutableLiveData<Product> = MutableLiveData()
//    val error: MutableSharedFlow<String> = MutableSharedFlow()

    val addTask: MutableSharedFlow<Unit> = MutableSharedFlow()

//    init {
//        getProductById(2)
//    }

    // this initializes when the app is in RESUME state
    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun navigateToAddTaskFragment() {
        viewModelScope.launch {
            try {
                addTask.emit(Unit)
            } catch (e: Exception) {
                error.emit(e.message.toString())
                Log.d("debugging", e.message.toString())
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try{
                val res = safeApiCall { productRepo.getAllProducts() }
                products.value = res as MutableList<Product>
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

//    fun getProducts() {
//        viewModelScope.launch {
//            try {
//                val res = productRepo.getAllProducts()
//                products.value = res as MutableList<Product>
//            } catch (e: Exception) {
//                error.emit(e.message.toString())
//                Log.d("debugging", e.message.toString())
//            }
//        }
//    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            try {
                val res = productRepo.getProductById(id)
                res.let {
                    product.value = it
                    Log.d("debugging", it.toString())
                }
            } catch (e: Exception) {
                error.emit(e.message.toString())
                Log.d("debugging", e.message.toString())
            }
        }
    }

//    class Provider(val productRepo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(productRepo) as T
//        }
//    }
}