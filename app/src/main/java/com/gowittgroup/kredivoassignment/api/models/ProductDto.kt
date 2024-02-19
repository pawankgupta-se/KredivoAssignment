package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("bill_type")
    val billType: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("nominal")
    val nominal: String,
    @SerializedName("operator")
    val telecomOperator: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("sequence")
    val sequence: Int
)