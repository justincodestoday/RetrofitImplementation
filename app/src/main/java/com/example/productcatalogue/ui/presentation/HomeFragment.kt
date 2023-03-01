package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productcatalogue.R
import com.example.productcatalogue.ui.adapter.ProductAdapter
import com.example.productcatalogue.databinding.FragmentHomeBinding
import com.example.productcatalogue.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ProductAdapter
    override val viewModel: HomeViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_home

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        setupAdapter()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupAdapter()
//    }

    override fun onBindData(view: View) {
        super.onBindData(view)

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProduct(it)
        }


        binding?.febAdd?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment()
            navController.navigate(action)

        }

        setFragmentResultListener("from_add_product"){_,result ->
            val refresh = result.getBoolean("refresh")
            if(refresh){
                viewModel.getProducts()
            }
        }

        setFragmentResultListener("from_update_product"){_,result ->
            val refresh = result.getBoolean("refresh")
            if(refresh){
                viewModel.getProducts()
            }
        }


    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf()) {
            val action = HomeFragmentDirections.actionHomeFragmentToEditProductFragment(it.id!!.toString())
            navController.navigate(action)
        }
        binding?.rvProducts?.adapter = adapter
        binding?.rvProducts?.layoutManager = layoutManager
    }

}