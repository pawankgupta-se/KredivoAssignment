package com.gowittgroup.kredivoassignment.domain.models

data class OrderItem(
    val aggregatedPrice: String,
    val category: String,
    val name: String,
    val pId: Int,
    val quantity: Int,
    val sku: String,
    val skuType: Int,
    val status: String,
    val totalAmount: String,
    val unitPrice: String
)