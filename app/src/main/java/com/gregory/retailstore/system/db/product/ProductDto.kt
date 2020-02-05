package com.gregory.retailstore.system.db.product

import android.os.Parcel
import android.os.Parcelable


// TODO add description
class ProductDto(
    val id: Long,
    val name: String,
    val category: ProductModel.CATEGORY,
    val price: Float,
    val imageUrl: String?
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().orEmpty(),
        ProductModel.CATEGORY.valueOf(parcel.readString().orEmpty()),
        parcel.readFloat(),
        parcel.readString()
    ) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeFloat(price)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDto> {
        override fun createFromParcel(parcel: Parcel): ProductDto {
            return ProductDto(parcel)
        }

        override fun newArray(size: Int): Array<ProductDto?> {
            return arrayOfNulls(size)
        }

        fun fromModel(productModel: ProductModel): ProductDto {
            productModel.run {
                return ProductDto(id, name, category, price, imageUrl)
            }
        }

        fun toModel(productDto: ProductDto): ProductModel {
            productDto.run {
                return ProductModel(id, name, category, price, imageUrl)
            }
        }
    }
}