package com.example.productcatelogue.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductRepository) : BaseViewModel() {
    val products: MutableLiveData<List<Product>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }
    fun getProducts() {
        viewModelScope.launch {
//                val res = repo.getAllProducts()
//                products.value = res
                val res = safeApiCall{repo.getAllProducts()}
            res?.let{
                products.value=it
            }
//                val res = repo.getProductById(10)
//                products.value= listOf(res
        }
    }
    fun deleteProducts(id:String){
        viewModelScope.launch {
            repo.deleteProduct(id)
            finishFromDelete.emit(Unit)
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(repo) as T
//        }
//    }
}