package com.gowittgroup.kredivoassignment.api.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("products")
    val product: List<ProductDto>
)