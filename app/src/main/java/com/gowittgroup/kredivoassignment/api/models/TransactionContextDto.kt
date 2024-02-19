package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class TransactionContextDto(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("applied_payment_type")
    val appliedPaymentType: String,
    @SerializedName("checkout_amount")
    val checkoutAmount: String,
    @SerializedName("expiration_time")
    val expirationTime: String,
    @SerializedName("item_list")
    val itemList: List<Item>,
    @SerializedName("merchant_details")
    val merchantDetails: MerchantDetails,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("transaction_status")
    val transactionStatus: String,
    @SerializedName("transaction_token")
    val transactionToken: String
)