package com.example.productcatelogue.ui.viewModel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class HomeViewModel(private val repo: ProductRepository) : BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        getProduct()
    }
    fun getProduct() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let {
                products.value = res as MutableList<Product>
            }
//            try{
//                val res = repo.getAllProducts()
//                val res = repo.getProductById(1000)
//                products.value = res as MutableList<Product>
//                Log.d("debug",res.toString())
//            }catch(e:Exception){
//                error.emit(e.message.toString())
//                Log.d("de",e.message.toString())
//            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }

    }
}