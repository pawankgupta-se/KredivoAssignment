package com.gowittgroup.kredivoassignment.domain.usecases

import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import com.gowittgroup.kredivoassignment.domain.exceptions.InternalErrorException
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.repositories.ProductRepository
import com.gowittgroup.kredivoassignment.domain.models.Response
import javax.inject.Inject

class GetProductsByCodeUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(code:String): Response<Product> {
        return try {
            Response.Success(repository.getProducts().first{it.productCode == code})
        } catch (e: BadRequestException) {
            Response.Error("${e.message}")
        } catch (e: InternalErrorException) {
            Response.Error("${e.message}")
        }
    }
}