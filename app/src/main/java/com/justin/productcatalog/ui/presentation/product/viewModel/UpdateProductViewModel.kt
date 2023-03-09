package com.justin.productcatalog.ui.presentation.product.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
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

    fun updateProductWithNewImage(
        id: String,
        product: Product,
        imageUri: Uri?
    ) {
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.ENGLISH)
        val date = Date()
        val imageName = formatter.format(date)
        imageUri?.let {
            StorageService.addImage(it, imageName) { status ->
                if (!status) {
                    viewModelScope.launch {
                        error.emit("Image Upload Failed")
                    }
                } else {
                    viewModelScope.launch {
                        safeApiCall {
                            productRepo.updateProduct(
                                id,
                                product.copy(thumbnail = imageName)
                            )
                        }
                        finish.emit(Unit)
                    }
                }
            }
        }
    }

    fun deleteImage(product: Product) {
        product.thumbnail?.let {
            StorageService.deleteImage(it) { deletedStatus ->
                if (!deletedStatus) {
                    viewModelScope.launch {
                        error.emit("Image deletion failed")
                        Log.d("debugging", "not deleted")
                    }
                } else {
                    viewModelScope.launch {
                        success.emit("Image successfully deleted")
                        Log.d("debugging", "deleted")
                    }
                }
            }
        }
    }

//    fun updateProduct(
//        id: String,
//        product: Product
//    ) {
//        viewModelScope.launch {
//            try {
//                safeApiCall { productRepo.updateProduct(id, product) }
//                finish.emit(Unit)
//            } catch (e: Exception) {
//                error.emit(e.message.toString())
//            }
//        }
//    }

//    class Provider(private val productRepo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return UpdateProductViewModel(productRepo) as T
//        }
//    }
}