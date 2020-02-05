package com.gregory.retailstore.system.api

import com.google.gson.Gson
import com.gregory.retailstore.system.Utils
import com.gregory.retailstore.system.db.product.ProductDto

/**
 * Normally, this class would fetch products from a distant server. For the case of this example, we
 * will simply load a local json file.
 */
class ProductApi {

    fun fetchAllProducts(): Array<ProductDto> {
        return Utils.loadJson(mockProductsFileName)?.let { jsonString ->
            Gson().fromJson(jsonString, emptyArray<ProductDto>().javaClass)
        } ?: emptyArray()
    }

    companion object {
        const val mockProductsFileName = "res/raw/products.json"
    }
}