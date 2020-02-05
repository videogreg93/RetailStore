package com.gregory.retailstore.system.db.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gregory.retailstore.R

@Entity
data class ProductModel(
    @PrimaryKey var id: Long = 0,
    @ColumnInfo(name = fnameN) var name: String = "",
    @ColumnInfo(name = fCategory) var category: CATEGORY = CATEGORY.ELECTRONICS,
    @ColumnInfo(name = fPrice) var price: Float = 0f,
    @ColumnInfo(name = fImageUrl) var imageUrl: String? = null
) {

    enum class CATEGORY(val stringId: Int) {
        ELECTRONICS(R.string.category_electronics),
        FURNITURE(R.string.category_furniture)
    }

    companion object Fields {
        const val fnameN = "name"
        const val fCategory = "category"
        const val fPrice = "price"
        const val fImageUrl = "image"
    }

}