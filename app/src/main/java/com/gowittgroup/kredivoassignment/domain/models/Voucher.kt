package com.gowittgroup.kredivoassignment.domain.models


data class Voucher(
    val endDate: String,
    val howToUse: String,
    val imageUrl: String,
    val maxDiscount: String,
    val minTransactionAmount: String,
    val name: String,
    val percentage: Int,
    val startDate: String,
    val termsAndCondition: String,
    val usageCount: Int,
    val voucherCode: String
){
    companion object{
        val DEFAULT = Voucher(
            endDate = "",
            howToUse = "",
            imageUrl = "",
            maxDiscount = "",
            minTransactionAmount = "",
            name = "",
            percentage = 0,
            startDate = "",
            termsAndCondition = "",
            usageCount = 0,
            voucherCode = ""
        )
    }
}