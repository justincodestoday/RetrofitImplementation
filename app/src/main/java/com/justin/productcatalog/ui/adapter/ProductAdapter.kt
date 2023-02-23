package com.justin.productcatalog.ui.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justin.productcatalog.R
import com.justin.productcatalog.data.model.Product
import com.justin.productcatalog.databinding.ItemLayoutProductBinding
import com.justin.productcatalog.util.update
import java.util.concurrent.Executors

class ProductAdapter(
    private var items: MutableList<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    class ProductHolder(val binding: ItemLayoutProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setProducts(items: List<Product>) {
        this.items = items as MutableList<Product>
        update(emptyList(), items) { task1, task2 ->
            task1.id == task2.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutProductBinding.inflate(layoutInflater, parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = items[position]
        holder.binding.run {
            tvBrand.text = item.brand
            tvTitle.text = item.title

            Glide.with(holder.binding.root)
                .load(item.thumbnail)
                .placeholder(R.drawable.insert_photo)
                .into(ivImage)

//            val executor = Executors.newSingleThreadExecutor()
//            val handler = Handler(Looper.getMainLooper())
//            var image: Bitmap? = null
//            executor.execute {
//                val imageURL = item.thumbnail
//                try {
//                    val `in` = java.net.URL(imageURL).openStream()
//                    image = BitmapFactory.decodeStream(`in`)
//                    handler.post {
//                        ivImage.setImageBitmap(image)
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
        }
    }
}