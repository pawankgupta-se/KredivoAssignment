package com.gowittgroup.kredivoassignment.data.repositories

import com.gowittgroup.kredivoassignment.api.models.TransactionContextDto
import com.gowittgroup.kredivoassignment.api.models.VoucherDto
import com.gowittgroup.kredivoassignment.data.datasources.RechargeDataSource
import com.gowittgroup.kredivoassignment.data.datasources.VoucherDataSource
import com.gowittgroup.kredivoassignment.data.mappers.toTransactionContext
import com.gowittgroup.kredivoassignment.data.mappers.toVoucher
import com.gowittgroup.kredivoassignment.domain.models.Response
import com.gowittgroup.kredivoassignment.domain.models.TransactionDetails
import com.gowittgroup.kredivoassignment.domain.repositories.RechargeRepository
import javax.inject.Inject

class RechargeRepositoryImpl @Inject constructor(private val dataSource: RechargeDataSource) :
    RechargeRepository {
    override suspend fun recharge(): TransactionDetails =
        dataSource.recharge().toTransactionContext()

}