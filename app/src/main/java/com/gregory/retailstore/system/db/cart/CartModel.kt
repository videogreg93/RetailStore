package com.gregory.retailstore.system.db.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartModel(
    @PrimaryKey(autoGenerate = true) var id: Long = -1,
    @ColumnInfo(name = fproduct_id) var productId: Long = -1,
    @ColumnInfo(name = fquantity) var quantity: Int = 0
) {

    companion object Fields {
        const val fproduct_id = "product_id"
        const val fquantity = "quantity"
    }
}