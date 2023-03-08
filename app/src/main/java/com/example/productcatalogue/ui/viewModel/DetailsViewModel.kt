package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.CartItem
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.FirebaseCartRepository
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.data.repository.ProductRepositoryImplementation
import com.example.productcatalogue.data.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: ProductRepository,
    private val cartRepo: FirebaseCartRepository,
    private val authService: AuthService
) : BaseViewModel() {
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
            safeApiCall { repo.deleteProduct(id) }
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            val uid = authService.getUid()
            val cartItem = product.value?.let {
                CartItem(it.id!!, it.title, 1)
            }
            if (uid != null && cartItem != null) {
                safeApiCall { cartRepo.addProductToCart(uid, cartItem) }
            }
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return DetailsViewModel(repo) as T
//        }
//    }
}