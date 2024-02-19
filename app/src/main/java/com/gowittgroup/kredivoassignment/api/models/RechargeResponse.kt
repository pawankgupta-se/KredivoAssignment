package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class RechargeResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("transaction_context")
    val transactionContextDto: TransactionContextDto
)