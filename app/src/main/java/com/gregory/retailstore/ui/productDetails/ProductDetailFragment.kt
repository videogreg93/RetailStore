package com.gregory.retailstore.ui.productDetails


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.product.ProductDto
import com.gregory.retailstore.system.toPrice
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : Fragment(), ProductDetailPresenter.View {
    override var presenter: ProductDetailPresenter = ProductDetailPresenter(this)

    private val args: ProductDetailFragmentArgs by navArgs()
    private var product: ProductDto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        product_detail_price.text = productDto.price.toPrice()

        product_detail_cart_button.setOnClickListener {
            presenter.addProductToCart(productDto)
        }
    }

    override fun onProductAddedSuccess(product: ProductDto) {
        Toast.makeText(
            context,
            getString(R.string.product_detail_added_to_cart),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onProductAddedFailed(product: ProductDto) {
        Toast.makeText(context, getString(R.string.product_detail_error), Toast.LENGTH_SHORT).show()
        Log.e(ProductDetailFragment::class.toString(), "Could not add ${product.name} to cart")
    }
}
