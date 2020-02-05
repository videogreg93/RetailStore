package com.gregory.retailstore.system.db.product


// TODO make parcelable
data class ProductDto(
    val id: Long,
    val name: String,
    val category: ProductModel.CATEGORY,
    val price: Float,
    val imageUrl: String?
) {

    companion object {
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