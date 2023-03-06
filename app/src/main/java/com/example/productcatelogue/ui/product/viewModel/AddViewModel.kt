package com.example.productcatelogue.ui.product.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductFirebaseRepositoryImpl
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.utils.Utils.validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(repo: ProductRepository) :
    BaseProductViewModel(repo) {

    fun addProduct(
        product: Product
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

            )
        viewModelScope.launch {
            if (validationStatus) {
                safeApiCall { repo.addProduct(product) }
                finish.emit(Unit)
            } else {
                error.emit("please provide all the information")
            }

        }
    }

//    class Provider(private val repo: ProductFirebaseRepositoryImpl) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return AddViewModel(repo) as T
//        }
//    }

}