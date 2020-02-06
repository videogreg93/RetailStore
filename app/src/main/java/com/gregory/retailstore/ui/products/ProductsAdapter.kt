package com.gregory.retailstore.ui.products

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.product.ProductDto
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter(val listener: ((ProductDto) -> Unit)) :
    ListAdapter<ProductDto, ProductsAdapter.ProductViewHolder>(ProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProductDto) {
            Glide.with(view)
                .load(item.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.product_image_progress.visibility = View.GONE
                        return false
                    }

                })
                .into(view.product_image)

            view.product_title.text = item.name
            view.product_category.text = view.context.getString(item.category.stringId)
            view.product_price.text = item.price.toString()

            view.setOnClickListener {
                listener.invoke(item)
            }

        }
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<ProductDto>() {

        override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem == newItem
        }
    }
}