package com.gowittgroup.kredivoassignment.data.datasources

import android.util.Log
import com.gowittgroup.kredivoassignment.api.ApiService
import com.gowittgroup.kredivoassignment.api.models.TransactionContextDto
import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import com.gowittgroup.kredivoassignment.domain.exceptions.InternalErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RechargeDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RechargeDataSource {
    override suspend fun recharge(): TransactionContextDto = withContext(Dispatchers.IO) {
        try {
            val res = apiService.recharge()
            if (res.status == Constants.STATUS_SUCCESS) {
                return@withContext res.transactionContextDto
            } else {
                Log.d(TAG, "Bad request")
                throw BadRequestException("Bad request")
            }
        } catch (e: Exception) {
            Log.d(TAG, "${e.message}")
            throw InternalErrorException("Unable to recharge.")
        }

    }

    companion object {
        val TAG = RechargeDataSourceImpl::class.simpleName
    }
}