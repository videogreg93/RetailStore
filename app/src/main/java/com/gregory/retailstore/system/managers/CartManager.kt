package com.gregory.retailstore.system.managers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gregory.retailstore.system.db.cart.CartDao
import com.gregory.retailstore.system.db.cart.CartDto
import com.gregory.retailstore.system.db.cart.CartModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartManager(private val cartDao: CartDao, private val productManager: ProductManager) {

    /**
     * Returns a live data which contains an observable cart. In reality,
     * this method returns an empty live data, which it then posts to in a coroutine that
     * maps cartModels to CartDtos
     */
    fun getCart(): LiveData<List<CartDto>> {
        val liveData = MediatorLiveData<List<CartDto>>()
        liveData.addSource(cartDao.getCart()) { cartModels ->
            CoroutineScope(Dispatchers.Default).launch {
                val cart = cartModels.mapNotNull { cartModel ->
                    productManager.getProductById(cartModel.productId)?.let { product ->
                        CartDto(product, cartModel.quantity)
                    }
                }
                liveData.postValue(cart)
            }
        }
        return liveData
    }

    /**
     * @return returns true if successful, false otherwise
     */
    suspend fun addToCart(productId: Long): Boolean {
        return try {
            val cartModel = cartDao.findByProductId(productId) ?: CartModel(productId)
            cartModel.quantity++
            cartDao.insert(cartModel)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * @return returns true if successful, false otherwise
     */
    suspend fun updateCart(cartDto: CartDto): Boolean {
        return try {
            if (cartDto.quantity == 0) {
                cartDao.delete(cartDto.productDto.id)
            } else {
                cartDao.insert(CartModel(cartDto.productDto.id, cartDto.quantity))
            }
            true
        } catch (e: Exception) {
            Log.d(CartManager::class.toString(), e.message)
            false
        }
    }

    suspend fun clearCart() {
        cartDao.deleteAll()
    }

}