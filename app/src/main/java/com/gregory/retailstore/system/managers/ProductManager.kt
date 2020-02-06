package com.gregory.retailstore.system.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gregory.retailstore.system.api.ProductApi
import com.gregory.retailstore.system.db.product.ProductDao
import com.gregory.retailstore.system.db.product.ProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductManager(private val api: ProductApi, private val dao: ProductDao) {

    /**
     * Get all products from the distant server, and cache them to our local
     * database.
     */
    fun fetchAllProducts() {
        CoroutineScope(Dispatchers.Default).launch {
            val products = api.fetchAllProducts().map { ProductDto.toModel(it) }
            dao.insertProducts(*products.toTypedArray())
        }
    }

    /**
     * Returns a live data of all products, sorted by their category
     */
    fun getSortedLiveProducts(): LiveData<List<ProductDto>> {
        return Transformations.map(dao.getAllProduct()) { products ->
            products.map { ProductDto.fromModel(it) }.sortedBy { it.category }
        }
    }

    suspend fun getProductById(id: Long): ProductDto? {
        return dao.findById(id)?.let {
            ProductDto.fromModel(it)
        }
    }
}