package com.example.productcatalogue.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productcatalogue.data.model.Dept
import com.example.productcatalogue.data.model.DeptWithStudent
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.data.model.Student
import com.example.productcatalogue.data.service.AuthService
import com.example.productcatalogue.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProductRepository,
    private val authRepo: AuthService
) :
    BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()

    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun onRefresh() {
        getProducts()
    }

    fun addDummy() {
        viewModelScope.launch {
            val students = mutableListOf<Student>()
            val k = (1..10).random()
            for (i in 1..k) {
                students.add(Student(id = "id$i", name = "Name$i"))
            }
            repo.addDummy(
                DeptWithStudent(
                    dept = Dept("id", "Dept Name"),
                    students = students
                )
            )
        }
    }

    fun logout() {
        authRepo.deAuthenticate()
    }

    fun getProducts() {
        viewModelScope.launch {
            val res = safeApiCall { repo.getAllProducts() }
            res?.let {
                products.value = it.toMutableList()
            }
//            try {
//                val res = repo.getAllProducts()
//                products.value = res.toMutableList()
//            } catch (e: Exception) {
////                e.message
////                e.printStackTrace()
////                error.emit(e.message.toString())
//                error2.emit("something is wrong")
//                Log.d("debugging", e.message.toString())
//            }
        }
    }

//    class Provider(private val repo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(repo) as T
//        }
//    }
}