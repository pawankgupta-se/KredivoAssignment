package com.gowittgroup.kredivoassignment.domain.repositories

import com.gowittgroup.kredivoassignment.domain.models.TransactionDetails

interface RechargeRepository {
     suspend fun recharge(): TransactionDetails
}