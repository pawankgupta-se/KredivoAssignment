package com.gowittgroup.kredivoassignment.data.datasources

import android.util.Log
import com.gowittgroup.kredivoassignment.api.ApiService
import com.gowittgroup.kredivoassignment.api.models.ProductDto
import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    ProductDataSource {
    override suspend fun getProducts(): List<ProductDto> =
        withContext(Dispatchers.IO) {
            try {
                val res = apiService.getProducts()
                if (res.status == Constants.STATUS_SUCCESS) {
                    return@withContext res.product
                } else {
                    Log.d(TAG, "Bad request")
                    throw BadRequestException("Bad request")

                }
            } catch (e: Exception) {
                Log.d(TAG, "${e.message}")
                throw InterruptedException("Unable to fetch product")
            }
        }


    companion object {
        val TAG = ProductDataSourceImpl::class.simpleName
    }
}