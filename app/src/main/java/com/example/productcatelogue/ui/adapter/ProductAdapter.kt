package com.example.productcatelogue.ui.adapter

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productcatelogue.R
import com.example.productcatelogue.data.model.Product
import com.example.productcatelogue.databinding.ItemLayoutProductBinding
import com.example.productcatelogue.utils.update
import java.util.concurrent.Executors

class ProductAdapter(private var products: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ItemTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutProductBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val product = products[position]

        holder.binding.run {
            tvTitle.text = product.title
            tvBrand.text = product.brand
            tvDes.text = product.description
            tvPrice.text = "RM ${product.price}"
            Glide.with(holder.binding.root)
                .load(product.thumbnail)
                .placeholder(R.drawable.insert_photo)
                .into(image)



//            val executor = Executors.newSingleThreadExecutor()
//            val handler = Handler(Looper.getMainLooper())
//            executor.execute {
//                val imageURL = product.thumbnail
//                val `in` = java.net.URL(imageURL).openStream()
//                val bitmap = BitmapFactory.decodeStream(`in`)
//                handler.post {
//                    image.setImageBitmap(bitmap)
//                }
//            }
        }
    }

    fun setProducts(items: MutableList<Product>) {
        this.products = items.toMutableList()

        update(emptyList(), items) { task1, task2 ->
            task1.id == task2.id
        }
    }

    override fun getItemCount() = products.size

    class ItemTaskHolder(val binding: ItemLayoutProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}
