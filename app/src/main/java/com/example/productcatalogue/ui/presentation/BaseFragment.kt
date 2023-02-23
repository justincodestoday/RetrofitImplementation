package com.example.productcatalogue.ui.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.productcatalogue.ui.viewModel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindView(view, savedInstanceState)
        onBindData(view)
    }

    fun onBindView(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.error.collect {
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun onBindData(view: View) {}
}