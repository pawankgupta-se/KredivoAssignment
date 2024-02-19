package com.gowittgroup.kredivoassignment.data.repositories

import com.gowittgroup.kredivoassignment.api.models.ProductDto
import com.gowittgroup.kredivoassignment.data.datasources.ProductDataSource
import com.gowittgroup.kredivoassignment.data.mappers.toProduct
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.repositories.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(private val dataSource: ProductDataSource) :
    ProductRepository {
    override suspend fun getProducts(): List<Product> =
        dataSource.getProducts().map(ProductDto::toProduct)

}
