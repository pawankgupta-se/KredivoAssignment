package com.gowittgroup.kredivoassignment.api.models


import com.google.gson.annotations.SerializedName

data class MerchantDetails(
    @SerializedName("logo_url")
    val logoUrl: String,
    @SerializedName("name")
    val name: String
)