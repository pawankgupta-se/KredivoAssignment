package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("aggregated_price")
    val aggregatedPrice: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("p_id")
    val pId: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("sku_type")
    val skuType: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("total_amount")
    val totalAmount: String,
    @SerializedName("unit_price")
    val unitPrice: String
)