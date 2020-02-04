package com.gregory.retailstore.system.db.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductModel(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = fields.name) val name: String,
    @ColumnInfo(name = fields.category) val category: CATEGORY,
    @ColumnInfo(name = fields.price) val price: Float,
    @ColumnInfo(name = fields.imageUrl) val imageUrl: String?
) {

    enum class CATEGORY {
        ELECTRONICS,
        FURNITURE
    }

    companion object fields {
        const val name = "name"
        const val category = "category"
        const val price = "price"
        const val imageUrl = "image"
    }

}