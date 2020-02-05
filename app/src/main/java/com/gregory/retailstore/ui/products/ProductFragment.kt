package com.gregory.retailstore.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.product.ProductDto
import kotlinx.android.synthetic.main.fragment_home.*

class ProductFragment : Fragment() {
    private val productViewModel: ProductViewModel by activityViewModels()

    private val productsAdapter = ProductsAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products_recyclerview.layoutManager = LinearLayoutManager(context)
        products_recyclerview.adapter = productsAdapter

        productViewModel.products.observe(viewLifecycleOwner, Observer { products ->
            productsAdapter.submitList(products)
        })
    }

    private fun onItemClicked(item: ProductDto) {
        // TODO send item to details fragment
        findNavController().navigate(R.id.action_navigation_home_to_productDetailFragment)
    }
}