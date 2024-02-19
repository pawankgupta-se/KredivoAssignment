package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class VoucherDto(
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("how_to_use")
    val howToUse: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("max_discount")
    val maxDiscount: String,
    @SerializedName("min_transaction_amount")
    val minTransactionAmount: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentage")
    val percentage: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("terms_and_condition")
    val termsAndCondition: String,
    @SerializedName("usage_count")
    val usageCount: Int,
    @SerializedName("voucher_code")
    val voucherCode: String
)