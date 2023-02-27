package com.example.productcatalogue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productcatalogue.R
import com.example.productcatalogue.data.model.Product
import com.example.productcatalogue.databinding.ItemProductLayoutBinding
import com.example.productcatalogue.utils.Utils.update

class ProductAdapter(private var products: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ItemProductHolder>() {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductLayoutBinding.inflate(layoutInflater, parent, false)
        return ItemProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemProductHolder, position: Int) {
        val product = products[position]
        holder.binding.run {
            val caption = "${product.brand} ${product.category} at RM${product.price}"
            val rating = "⭐${product.rating}"
            tvTitle.text = product.title
            tvCaption.text = caption
            tvRating.text = rating
            if (product.thumbnail.isNotEmpty() && URLUtil.isValidUrl(product.thumbnail)) {
                Glide.with(holder.binding.root)
                    .load(product.thumbnail).into(ivImage)
            } else {
                Glide.with(holder.binding.root)
                    .load(R.drawable.ic_empty_folder).into(ivImage)
            }

            cvTaskItem.setOnClickListener {
                listener?.onClick(product)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    fun setProducts(products: MutableList<Product>) {
        val oldProducts = this.products
        this.products = products
        update(oldProducts, products) { product1, product2 ->
            product1.id == product2.id
        }
    }

    class ItemProductHolder(val binding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(product: Product)
    }
}