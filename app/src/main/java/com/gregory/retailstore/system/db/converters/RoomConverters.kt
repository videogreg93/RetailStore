package com.gregory.retailstore.system.db.converters

import androidx.room.TypeConverter
import com.gregory.retailstore.system.db.product.ProductModel

class RoomConverters {
    @TypeConverter
    fun categoryToString(category: ProductModel.CATEGORY): String? {
        return category.name
    }

    @TypeConverter
    fun stringToCategory(category: String?): ProductModel.CATEGORY? {
        return ProductModel.CATEGORY.valueOf(category.orEmpty())
    }
}