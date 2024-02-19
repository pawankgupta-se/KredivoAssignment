package com.gowittgroup.kredivoassignment.data.datasources

import com.gowittgroup.kredivoassignment.api.models.VoucherDto
import kotlin.jvm.Throws

interface VoucherDataSource {
    @Throws
    suspend fun getVouchers(): List<VoucherDto>
}