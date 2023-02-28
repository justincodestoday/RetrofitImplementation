package com.justin.productcatalog.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import com.justin.productcatalog.util.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(productRepo: ProductRepository) :
    BaseProductViewModel(productRepo) {
//    val completeAdd: MutableSharedFlow<Unit> = MutableSharedFlow()

    fun addProduct(
        product: Product
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
        viewModelScope.launch {
            try {
                safeApiCall { productRepo.addProduct(product) }
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