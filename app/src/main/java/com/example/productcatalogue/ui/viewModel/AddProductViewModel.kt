package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.repository.ProductRepository
import com.example.productcatalogue.utils.Utils.validate
import kotlinx.coroutines.launch

class AddProductViewModel(private val repo: ProductRepository) : BaseViewModel() {

    fun addProduct(
        title: String,
        desc: String,
        price: String,
        disc: String,
        rating: String,
        stock: String,
        brand: String,
        cat: String,
    ) {
        val validationStatus = validate(
            title, desc, price, disc, rating, stock, brand, cat
        )
        viewModelScope.launch {
            if (validationStatus) {
                val product = Product(
                    null, title, brand, cat, desc,
                    price.toFloat(), disc.toFloat(),
                    rating.toFloat(), stock.toInt(), ""
                )

                repo.addProduct(product)

            } else {

                error.emit("Please fill in every details")
            }
        }
    }

    class Provider(val repo: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(repo) as T
        }
    }
}