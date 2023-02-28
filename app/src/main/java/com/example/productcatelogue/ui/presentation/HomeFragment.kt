package com.example.productcatelogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productcatelogue.R
import com.example.productcatelogue.data.api.RetrofitClient
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.data.repository.ProductRepository
import com.example.productcatelogue.databinding.FragmentHomeBinding
import com.example.productcatelogue.ui.adapter.ProductAdapter
import com.example.productcatelogue.ui.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()

    //    {
//        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }
    private lateinit var adapter: ProductAdapter
    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
        fragmentResultListener()

//        binding?.

        binding?.btnAdd?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            navController.navigate(action)
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProduct(it)
        }
        lifecycleScope.launch {
            viewModel.error.collect {
                val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }

        viewModel.products.observe(viewLifecycleOwner) {
            Log.d("debugging", it.toString())
        }
    }

    fun fragmentResultListener() {
        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }
        setFragmentResultListener("from_update_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }
        setFragmentResultListener("from_delete_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getProducts()
            }
        }
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf(), {

            val action = HomeFragmentDirections.actionHomeFragmentToEditProductFragment(it.id!!)
            navController.navigate(action)
        }, {
            lifecycleScope.launch {
                viewModel.deleteProducts(it.id!!)
                viewModel.finishFromDelete.collect {
                    val bundle = Bundle()
                    bundle.putBoolean("refresh", true)
                    setFragmentResult("from_delete_product", bundle)
                }
            }
        })
        binding?.rvItems?.adapter = adapter
        binding?.rvItems?.layoutManager = layoutManager
    }
}