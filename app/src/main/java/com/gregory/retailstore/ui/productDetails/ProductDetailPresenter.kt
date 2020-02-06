package com.gregory.retailstore.ui.productDetails

import com.gregory.retailstore.system.db.product.ProductDto
import com.gregory.retailstore.system.managers.RetailStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailPresenter(private val view: View) {
    private val manager = RetailStoreManager.cartManager

    fun addProductToCart(product: ProductDto) {
        CoroutineScope(Dispatchers.Default).launch {
            val success = manager.addToCart(product.id)
            withContext(Dispatchers.Main) {
                if (success) {
                    view.onProductAddedSuccess(product)
                } else {
                    view.onProductAddedFailed(product)
                }
            }
        }
    }

    interface View {
        var presenter: ProductDetailPresenter

        fun onProductAddedSuccess(product: ProductDto)
        fun onProductAddedFailed(product: ProductDto)
    }
}