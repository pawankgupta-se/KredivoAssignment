package com.gowittgroup.kredivoassignment.api

import com.gowittgroup.kredivoassignment.api.models.ProductsResponse
import com.gowittgroup.kredivoassignment.api.models.RechargeResponse
import com.gowittgroup.kredivoassignment.api.models.VoucherResponse

interface ApiService {
    fun getProducts(): ProductsResponse
    fun getVouchers(): VoucherResponse
    fun recharge(): RechargeResponse
}