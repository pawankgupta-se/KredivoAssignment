package com.gowittgroup.kredivoassignment.data.datasources

import com.gowittgroup.kredivoassignment.api.models.ProductDto
import kotlin.jvm.Throws

interface ProductDataSource {
    @Throws
    suspend fun getProducts(): List<ProductDto>
}