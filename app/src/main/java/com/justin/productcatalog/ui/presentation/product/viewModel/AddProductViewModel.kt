package com.justin.productcatalog.ui.presentation.product.viewModel

import android.net.Uri
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
class AddProductViewModel @Inject constructor(productRepo: ProductRepository) :
    BaseProductViewModel(productRepo) {
//    val completeAdd: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun addProduct(
        product: Product,
        imageUri: Uri?
//        brand: String,
//        category: String,
//        title: String,
//        description: String,
//        price: String,
//        discount: String,
//        rating: String,
//        stock: String,
////        thumbnail: String
    ) {
//        val validationStatus = validate(
//            brand,
//            category,
//            title,
//            description,
//            price,
//            discount,
//            rating,
//            stock,
//            thumbnail
//        )
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.ENGLISH)
        val date = Date()
        val imageName = formatter.format(date)
        viewModelScope.launch {
            imageUri?.let {
                StorageService.addImage(it, imageName) { status ->
                    if (!status) {
                        this.launch {
                            error.emit("Image Upload Failed")
                        }
                    }
                }
            }
            try {
                safeApiCall { productRepo.addProduct(product.copy(thumbnail = imageName)) }
                finish.emit(Unit)

//                if (validationStatus) {
//                    val _product =
//                        Product(
//                            null,
//                            brand,
//                            category,
//                            title,
//                            description,
//                            price.toFloat(),
//                            discount.toFloat(),
//                            rating.toFloat(),
//                            stock.toInt(),
//                            "",
//                            null
//                        )
//                } else {
//                    error.emit("Please fill in every detail")
//                }
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

//    class Provider(private val productRepo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return AddProductViewModel(productRepo) as T
//        }
//    }
}