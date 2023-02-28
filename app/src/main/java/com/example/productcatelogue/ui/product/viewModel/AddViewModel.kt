package com.example.productcatelogue.ui.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.ui.viewModel.BaseViewModel
import com.example.productcatelogue.utils.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(repo: ProductRepository) : BaseProductViewModel(repo) {

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
        val validationStatus = validate(
            product.brand,
            product.category,
            product.title,
            product.description,
            product.price.toString(),
            product.discountPercentage.toString(),
            product.rating.toString(),
            product.stock.toString(),

//            brand,
//            category,
//            title,
//            description,
//            price,
//            discount,
//            rating,
//            stock,
////            thumbnail
        )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall {repo.addProduct(product) }
                finish.emit(Unit)

//                val _product =
//                    Product(
//                        null,
//                        brand,
//                        category,
//                        title,
//                        description,
//                        price.toFloat(),
//                        discount.toFloat(),
//                        rating.toFloat(),
//                        stock.toInt(),
//                        "",
//                        null
//                    )
            } else {
                error.emit("Please fill in every detail")
            }
        }
    }

    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddViewModel(repo) as T
        }
    }

}