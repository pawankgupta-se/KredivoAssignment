package com.gowittgroup.kredivoassignment.domain.usecases

import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import com.gowittgroup.kredivoassignment.domain.exceptions.InternalErrorException
import com.gowittgroup.kredivoassignment.domain.models.Response
import com.gowittgroup.kredivoassignment.domain.models.TransactionDetails
import com.gowittgroup.kredivoassignment.domain.repositories.RechargeRepository
import javax.inject.Inject

class RechargeUseCase @Inject constructor(private val repository: RechargeRepository) {
    suspend operator fun invoke(): Response<TransactionDetails> {
        return try {
            Response.Success(repository.recharge())
        } catch (e: BadRequestException) {
            Response.Error("${e.message}")
        } catch (e: InternalErrorException) {
            Response.Error("${e.message}")
        }
    }
}