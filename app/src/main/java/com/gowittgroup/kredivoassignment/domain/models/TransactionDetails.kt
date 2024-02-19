package com.gowittgroup.kredivoassignment.domain.models

data class TransactionDetails(
    val amount: String,
    val appliedPaymentType: String,
    val checkoutAmount: String,
    val expirationTime: String,
    val orderItemList: List<OrderItem>,
    val vendorDetails: VendorDetails,
    val orderId: String,
    val transactionStatus: String,
    val transactionToken: String
){
    companion object {
        val DEFAULT = TransactionDetails(
            amount = "",
            appliedPaymentType = "",
            checkoutAmount = "",
            expirationTime = "",
            orderId = "",
            transactionStatus = "",
            transactionToken = "",
            vendorDetails = VendorDetails(logoUrl = "", name = ""),
            orderItemList = mutableListOf()
        )
    }
}