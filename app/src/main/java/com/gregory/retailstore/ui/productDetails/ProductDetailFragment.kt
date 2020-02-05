package com.gregory.retailstore.ui.productDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.product.ProductDto
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : Fragment() {

    val args: ProductDetailFragmentArgs by navArgs()
    var product: ProductDto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = args.product
        product?.let { populateView(it) }
    }

    private fun populateView(productDto: ProductDto) {
        Glide.with(this)
            .load(productDto.imageUrl)
            .into(product_detail_image)

        product_detail_title.text = productDto.name
        product_detail_category.text = getString(productDto.category.stringId)
        product_detail_price.text = productDto.price.toString()
    }
}
