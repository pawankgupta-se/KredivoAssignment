package com.gowittgroup.kredivoassignment.data.datasources

import android.util.Log
import com.gowittgroup.kredivoassignment.api.ApiService
import com.gowittgroup.kredivoassignment.api.models.VoucherDto
import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import com.gowittgroup.kredivoassignment.domain.exceptions.InternalErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VoucherDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    VoucherDataSource {
    override suspend fun getVouchers(): List<VoucherDto> =
        withContext(Dispatchers.IO) {
            try {
                val res = apiService.getVouchers()
                if (res.status == Constants.STATUS_SUCCESS) {
                    return@withContext res.data
                } else {
                    Log.d(TAG, "Bad request")
                    throw BadRequestException("Bad request")
                }
            } catch (e: Exception) {
                Log.d(TAG, "${e.message}")
                throw InternalErrorException("Unable to fetch vouchers")
            }
        }


    companion object {
        val TAG = VoucherDataSourceImpl::class.simpleName
    }
}