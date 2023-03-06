package com.example.productcatelogue.ui.product.viewModel

import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.repository.ProductFirebaseRepositoryImpl
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.ui.viewModel.BaseViewModel
import com.example.productcatelogue.utils.Utils
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


abstract class BaseProductViewModel(val repo:ProductRepository):BaseViewModel() {
    val finish:MutableSharedFlow<Unit> = MutableSharedFlow()

    fun validate(
        title:String,
        description:String,
        price:String,
        rating:String,
        stock:String,
        brand:String,
        category:String,
        discountPercentage:String
    ):Boolean{
        if(Utils.validate(title,description,price,rating,stock,brand,category,discountPercentage)){
            return true
        }
        viewModelScope.launch {
            error.emit("")
        }
        return false
    }
}