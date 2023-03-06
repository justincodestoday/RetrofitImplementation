package com.example.productcatelogue.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.productcatelogue.ui.product.viewModel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddFragment :BaseProductFragment() {
    override val viewModel: AddViewModel by viewModels()

    override fun onBindView(view: View, savedInstanceState: Bundle?) {
        super.onBindView(view, savedInstanceState)
        binding?.run {
            btnAdd.setOnClickListener {
                val product = getProduct()
                product?.let{
                    viewModel.addProduct(it)
                }

            }
        }

    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch{
            viewModel.finish.collect{
                val bundle = Bundle()
                bundle.putBoolean("refresh", true)
                setFragmentResult("from_add_item", bundle)
                navController.popBackStack()
            }
        }
    }
//    private lateinit var binding: FragmentAddBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentAddBinding.inflate(layoutInflater)
//        return binding.root
//    }
}