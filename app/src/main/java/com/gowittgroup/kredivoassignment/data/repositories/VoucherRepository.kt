package com.gowittgroup.kredivoassignment.data.repositories

import com.gowittgroup.kredivoassignment.api.models.VoucherDto
import com.gowittgroup.kredivoassignment.data.datasources.VoucherDataSource
import com.gowittgroup.kredivoassignment.data.mappers.toVoucher
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.domain.repositories.VoucherRepository
import javax.inject.Inject

class VoucherRepositoryImpl @Inject constructor(private val dataSource: VoucherDataSource) :
    VoucherRepository {
    override suspend fun getVouchers(): List<Voucher> =
        dataSource.getVouchers().map(VoucherDto::toVoucher)
}