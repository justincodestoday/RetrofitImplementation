package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.data.repository.ProductRepositoryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: ProductRepository) : BaseViewModel() {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: String) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            val res = safeApiCall { repo.deleteProduct(id) }
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return DetailsViewModel(repo) as T
//        }
//    }
}