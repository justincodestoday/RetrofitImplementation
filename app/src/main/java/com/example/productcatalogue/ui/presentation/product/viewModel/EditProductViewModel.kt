package com.example.productcatalogue.ui.presentation.product.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.FireStoreProductRepository
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.data.service.StorageService
import com.example.productcatalogue.utils.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(repo: ProductRepository) :
    BaseProductViewModel(repo) {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun editProduct(
        id: String,
        product: Product,
        imageUri: Uri?
    ) {
        val validationStatus = validate(
            product.title,
            product.description,
            product.brand,
            product.category,
            product.price.toString(),
            product.stock.toString(),
            product.discountPercentage.toString(),
            product.rating.toString()
        )
        viewModelScope.launch {
            if (imageUri == null) {
                if (validationStatus) {
                    safeApiCall { repo.editProduct(id, product) }
                    finish.emit(Unit)
                } else {
                    error.emit("Kindly provide all information")
                }
            } else {
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.ENGLISH)
                val date = Date()
                val imageName = formatter.format(date)

                imageUri.let {
                    StorageService.addImage(it, imageName) { status ->
                        if (!status) {
                            viewModelScope.launch {
                                error.emit("Image upload failed")
                            }
                        }
                    }
                }
                if (validationStatus) {
                    safeApiCall { repo.editProduct(id, product.copy(thumbnail = imageName)) }
                    finish.emit(Unit)
                } else {
                    error.emit("Kindly provide all information")
                }
            }
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            val res = safeApiCall { repo.getProductById(id) }
            res?.let {
                product.value = it
            }
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return EditProductViewModel(repo) as T
//        }
//    }
}