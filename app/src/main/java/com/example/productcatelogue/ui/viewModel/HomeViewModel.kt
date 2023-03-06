package com.example.productcatelogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.service.AuthService
import com.example.productcatelogue.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductRepository,private val auth: AuthService) : BaseViewModel() {
    val products: MutableLiveData<List<Product>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun onRefresh(){
        getProducts()
    }
    fun logout(){
        auth.deAuthenticate()
    }
    fun getProducts() {
        viewModelScope.launch {
            val res=safeApiCall { repo.getAllProducts() }
            res?.let{
                products.value=it
            }
        }
    }
    fun deleteProducts(id:String){
        viewModelScope.launch {
            repo.deleteProduct(id)
            finishFromDelete.emit(Unit)
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(repo) as T
//        }
//    }
}