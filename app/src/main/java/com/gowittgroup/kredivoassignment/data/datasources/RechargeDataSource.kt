package com.gowittgroup.kredivoassignment.data.datasources

import com.gowittgroup.kredivoassignment.api.models.TransactionContextDto


interface RechargeDataSource {
    suspend fun recharge(): TransactionContextDto
}