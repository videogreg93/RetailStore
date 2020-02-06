package com.gregory.retailstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.cart.CartDto
import kotlinx.android.synthetic.main.fragment_dashboard.*

class CartFragment : Fragment() {
    private val cartViewModel: CartViewModel by activityViewModels()

    private val cartAdapter = CartAdapter(::onQuantityChanged)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cart_recyclerview.layoutManager = LinearLayoutManager(context)
        cart_recyclerview.adapter = cartAdapter
        cartViewModel.cart.observe(viewLifecycleOwner, Observer { cart ->
            cartAdapter.submitList(cart)
        })
    }

    private fun onQuantityChanged(cartDto: CartDto) {
        // TODO
    }

}