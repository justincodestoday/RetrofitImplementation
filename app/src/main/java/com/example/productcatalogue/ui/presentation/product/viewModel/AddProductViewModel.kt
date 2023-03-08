package com.example.productcatalogue.ui.presentation.product.viewModel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.data.service.StorageService
import com.example.productcatalogue.utils.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    repo: ProductRepository,
) :
    BaseProductViewModel(repo) {
    fun addProduct(
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
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.ENGLISH)
        val date = Date()
        val imageName = formatter.format(date)

        viewModelScope.launch {
            imageUri?.let {
                StorageService.addImage(it, imageName) { status ->
                    if (!status) {
                        viewModelScope.launch {
                            error.emit("Image upload failed")
                        }
                    }
                }
            }
            if (validationStatus) {
                safeApiCall { repo.addProduct(product.copy(thumbnail = imageName)) }
                finish.emit(Unit)
            } else {
                error.emit("Kindly provide all information")
            }
        }
    }

    fun validateFail() {
        viewModelScope.launch {
            error.emit("Kindly provide all information")
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return AddProductViewModel(repo) as T
//        }
//    }
}