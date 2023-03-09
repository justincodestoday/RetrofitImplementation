package com.justin.productcatalog.ui.presentation.product.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.CartItem
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.FireStoreCartRepository
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.data.service.AuthService
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repo: ProductRepository,
    private val cartRepo: FireStoreCartRepository,
    private val authService: AuthService
) : BaseViewModel() {
    val product: MutableLiveData<Product> = MutableLiveData()
    val delete: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun getProduct(id: String) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            val uid = authService.getUid()
            val cartItem = product.value?.let {
                CartItem(it.id!!, it.title!!, 1)
            }

            if (uid != null && cartItem != null) {
                safeApiCall { cartRepo.addProductToCart(uid, cartItem) }
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            try {
                safeApiCall { repo.deleteProduct(id) }
                delete.emit(Unit)
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }
}