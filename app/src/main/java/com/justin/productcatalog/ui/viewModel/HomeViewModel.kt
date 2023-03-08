package com.justin.productcatalog.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.justin.productcatalog.data.model.Dept
import com.justin.productcatalog.data.model.DeptWithStudent
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.data.model.Student
import com.justin.productcatalog.data.service.AuthService
import com.justin.productcatalog.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepo: ProductRepository,
    private val authRepo: AuthService
) :
    BaseViewModel() {
    val products: MutableLiveData<MutableList<Product>> = MutableLiveData()
    val product: MutableLiveData<Product> = MutableLiveData()
//    val error: MutableSharedFlow<String> = MutableSharedFlow()

    val addTask: MutableSharedFlow<Unit> = MutableSharedFlow()

//    init {
//        getProductById(2)
//    }

    // this initializes when the app is in RESUME state
    override fun onViewCreated() {
        super.onViewCreated()
        getProducts()
    }

    fun navigateToAddTaskFragment() {
        viewModelScope.launch {
            try {
                addTask.emit(Unit)
            } catch (e: Exception) {
                error.emit(e.message.toString())
                logout.emit(Unit)
                Log.d("debugging", e.message.toString())
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val res = safeApiCall { productRepo.getAllProducts() }
                products.value = res as MutableList<Product>
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                safeApiCall { authRepo.deAuthenticate() }
            } catch (e: Exception) {
                error.emit(e.message.toString())
            }
        }
    }

    fun addDummy() {
        viewModelScope.launch {
            val students = mutableListOf<Student>()
            val k = (1..10).random()
            for(i in 1..k) {
                students.add(Student(id = "id$i", name = "Name$i"))
            }
            val deptName = listOf("Science", "Mathematics", "Business")
            val l = deptName.random()
            productRepo.addDummy(
                DeptWithStudent(
                    dept = Dept("id", l),
                    students = students
//                    student = listOf(
//                        Student("id", "Stu Name", "28"),
//                        Student("id", "Stu Name", "28")
//                    )
                )
            )
        }
    }

//    fun getProducts() {
//        viewModelScope.launch {
//            try {
//                val res = productRepo.getAllProducts()
//                products.value = res as MutableList<Product>
//            } catch (e: Exception) {
//                error.emit(e.message.toString())
//                Log.d("debugging", e.message.toString())
//            }
//        }
//    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            try {
                val res = productRepo.getProductById(id)
                res.let {
                    product.value = it
                    Log.d("debugging", it.toString())
                }
            } catch (e: Exception) {
                error.emit(e.message.toString())
                Log.d("debugging", e.message.toString())
            }
        }
    }

//    class Provider(val productRepo: ProductRepository) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return HomeViewModel(productRepo) as T
//        }
//    }
}