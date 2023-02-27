package com.justin.productcatalog.ui.presentation.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.repository.ProductRepository
import com.justin.productcatalog.ui.viewModel.BaseViewModel
import com.justin.productcatalog.util.Utils.validate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AddProductViewModel(private val productRepo: ProductRepository) : BaseProductViewModel() {
    val completeAdd: MutableSharedFlow<Unit> = MutableSharedFlow()

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
                safeApiCall { productRepo.addProduct(product) }
                completeAdd.emit(Unit)

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

    class Provider(val productRepo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(productRepo) as T
        }
    }
}