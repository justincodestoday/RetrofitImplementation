package com.fantasy.productcatalogue.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fantasy.productcatalogue.R
import com.fantasy.productcatalogue.data.api.RetrofitClient
import com.fantasy.productcatalogue.data.model.Product
import com.fantasy.productcatalogue.data.repository.ProductRepository
import com.fantasy.productcatalogue.databinding.FragmentHomeBinding
import com.fantasy.productcatalogue.ui.adapter.ProductAdapter
import com.fantasy.productcatalogue.ui.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapter: ProductAdapter
//    private lateinit var binding: FragmentHomeBinding
    override val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider(ProductRepository.getInstance(RetrofitClient.getInstance()))
    }

    override fun getLayoutResource() = R.layout.fragment_home

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentHomeBinding.inflate(layoutInflater)
//        return binding.root
//    }

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
//        viewModel.getProducts()
        setupAdapter()
        binding?.btnAdd?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddProductFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        setFragmentResultListener("from_add_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh) {
                viewModel.getProducts()
            }
        }

        setFragmentResultListener("from_update_product") { _, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh) {
                viewModel.getProducts()
            }
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setProduct(it)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)


//
//        lifecycleScope.launch {
//            viewModel.error.collect {
//                val snackbar = binding?.let { it1 -> Snackbar.make(it1.root, it, Snackbar.LENGTH_LONG) }
//                if (snackbar != null) {
//                    snackbar.show()
//                }
//            }
//        }


//    }
    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(mutableListOf()) {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateProductFragment(it.id!!)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding?.rvProducts?.adapter = adapter
        binding?.rvProducts?.layoutManager = layoutManager
    }
}
