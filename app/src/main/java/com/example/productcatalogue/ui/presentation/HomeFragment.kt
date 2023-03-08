package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productcatalogue.R
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    //    override val viewModel: HomeViewModel by viewModels {
//        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
//    }
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
        binding!!.btnAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddProduct()
            navController.navigate(action)
        }
        fragmentResultListener()

        binding!!.btnLogout.setOnClickListener {
            viewModel.logout()
            val action = HomeFragmentDirections.toLoginFragment()
            navController.navigate(action)
        }

        binding!!.btnAddDummy.setOnClickListener {
            viewModel.addDummy()
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProducts(it)
        }
    }

    private fun fragmentResultListener() {
        setFragmentResultListener("from_add_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.onRefresh()
            }
        }

        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.onRefresh()
            }
        }
    }

    private fun setupAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ProductAdapter(mutableListOf())
        adapter.listener = object : ProductAdapter.Listener {
            override fun onClick(product: Product) {
                val action = HomeFragmentDirections.actionHomeToDetails(product.id!!)
                navController.navigate(action)
            }
        }
        binding!!.rvProducts.adapter = adapter
        binding!!.rvProducts.layoutManager = layoutManager
    }
}