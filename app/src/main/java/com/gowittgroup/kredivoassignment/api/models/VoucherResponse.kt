package com.gowittgroup.kredivoassignment.api.models

import com.google.gson.annotations.SerializedName

data class VoucherResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<VoucherDto>

)
