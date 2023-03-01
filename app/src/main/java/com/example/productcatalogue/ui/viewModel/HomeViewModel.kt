package com.example.productcatalogue.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductRepository) : BaseViewModel() {

    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
//    val error: MutableSharedFlow<String> = MutableSharedFlow()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let{
                products.value = it.toMutableList()
            }
        }
    }


}