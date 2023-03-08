package com.justin.productcatalog.ui.presentation.product.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.FireStoreProductRepository
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.data.repository.ProductRepositoryImpl
import com.justin.productcatalog.data.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(productRepo: ProductRepository) :
    BaseProductViewModel(productRepo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProductById(id: String) {
        viewModelScope.launch {
            try {
                val res = productRepo.getProductById(id)
                res.let {
                    product.value = it
                }
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            try {
                safeApiCall { productRepo.deleteProduct(id) }
                finish.emit(Unit)
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

    fun updateProduct(
        id: String,
        product: Product
    ) {
        viewModelScope.launch {
            try {
                safeApiCall { productRepo.updateProduct(id, product) }
                finish.emit(Unit)
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

    fun getImageUri(imageName: String): Uri? {
        var thumbnail: Uri? = null
        viewModelScope.launch {
            StorageService.getImageUri(imageName) {
                thumbnail = it
            }
        }
        return thumbnail
    }

//    class Provider(private val productRepo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return UpdateProductViewModel(productRepo) as T
//        }
//    }
}