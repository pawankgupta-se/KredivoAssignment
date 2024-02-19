package com.gowittgroup.kredivoassignment.domain.repositories

import com.gowittgroup.kredivoassignment.domain.models.Voucher

interface VoucherRepository {
    suspend fun getVouchers(): List<Voucher>
}