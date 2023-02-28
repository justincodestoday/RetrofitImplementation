package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductRepository) : BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let {
                products.value = it.toMutableList()
            }
//            try {
//                val res = repo.getAllProducts()
//                products.value = res.toMutableList()
//            } catch (e: Exception) {
////                e.message
////                e.printStackTrace()
////                error.emit(e.message.toString())
//                error2.emit("something is wrong")
//                Log.d("debugging", e.message.toString())
//            }
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(repo) as T
//        }
//    }
}