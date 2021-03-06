package com.gregory.retailstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gregory.retailstore.R
import com.gregory.retailstore.system.db.cart.CartDto
import com.gregory.retailstore.system.toPrice
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(), CartPresenter.View {
    override val presenter: CartPresenter = CartPresenter(this)

    private val cartViewModel: CartViewModel by activityViewModels()

    private val cartAdapter = CartAdapter(::onQuantityChanged,::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cart_recyclerview.layoutManager = LinearLayoutManager(context)
        cart_recyclerview.adapter = cartAdapter
        cartViewModel.cart.observe(viewLifecycleOwner, Observer { cart ->
            cartAdapter.submitList(cart)
            val totalPrice = cart.sumByDouble { (it.productDto.price * it.quantity).toDouble() }.toPrice()
            cart_total_price.text = getString(R.string.cart_total_price, totalPrice)
        })

    }

    private fun onQuantityChanged(cartDto: CartDto) {
        presenter.updateQuantity(cartDto)
    }

    private fun onItemClicked(cartDto: CartDto) {
        val action = CartFragmentDirections.actionNavigationDashboardToProductDetailFragment(cartDto.productDto)
        findNavController().navigate(action)
    }

    override fun onError() {
        Toast.makeText(context, "Could not update quantity", Toast.LENGTH_SHORT).show()
    }

}