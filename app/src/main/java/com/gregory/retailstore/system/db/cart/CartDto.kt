package com.gregory.retailstore.system.db.cart

import android.os.Parcel
import android.os.Parcelable
import com.gregory.retailstore.system.db.product.ProductDto

class CartDto(
    val productDto: ProductDto,
    var quantity: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(ProductDto::class.java.classLoader),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(productDto, flags)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartDto> {
        override fun createFromParcel(parcel: Parcel): CartDto {
            return CartDto(parcel)
        }

        override fun newArray(size: Int): Array<CartDto?> {
            return arrayOfNulls(size)
        }
    }
}