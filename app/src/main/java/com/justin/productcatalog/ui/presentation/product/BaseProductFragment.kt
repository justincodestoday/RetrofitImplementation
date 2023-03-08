package com.justin.productcatalog.ui.presentation.product

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.lifecycleScope
import com.justin.productcatalog.R
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.databinding.FragmentAddProductBinding
import com.justin.productcatalog.ui.presentation.BaseFragment
import com.justin.productcatalog.util.Utils
import kotlinx.coroutines.launch

abstract class BaseProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override fun getLayoutResource(): Int = R.layout.fragment_add_product

    fun getProduct(): Product? {
        return binding?.run {
            val brand = etBrand.text.toString()
            val category = etCategory.text.toString()
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val price = etPrice.text.toString()
            val discount = etDiscount.text.toString()
            val rating = etRating.text.toString()
            val stock = etStock.text.toString()

            val validationStatus =
                Utils.validate(
                brand,
                category,
                title,
                description,
                price,
                discount,
                rating,
                stock
            )
            if (!validationStatus) {
                lifecycleScope.launch {
                    viewModel.error.emit("Please fill all the fields")
                }
                return null
            }
            Product(
                null,
                brand,
                category,
                title,
                description,
                price.toFloat(),
                discount.toFloat(),
                rating.toFloat(),
                stock.toInt()
            )
        }
    }

    fun ContentResolver.getFileName(fileUri: Uri): String {
        val cursor = this.query(fileUri, null, null, null, null)

        cursor?.let {
            val name = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            return cursor.getString(name)
        }
        cursor?.close()

        return "Unknown"
    }
}