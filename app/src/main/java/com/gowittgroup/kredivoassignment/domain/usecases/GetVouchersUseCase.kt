package com.gowittgroup.kredivoassignment.domain.usecases

import com.gowittgroup.kredivoassignment.domain.exceptions.BadRequestException
import com.gowittgroup.kredivoassignment.domain.exceptions.InternalErrorException
import com.gowittgroup.kredivoassignment.domain.models.Product
import com.gowittgroup.kredivoassignment.domain.repositories.ProductRepository
import com.gowittgroup.kredivoassignment.domain.models.Response
import com.gowittgroup.kredivoassignment.domain.models.Voucher
import com.gowittgroup.kredivoassignment.domain.repositories.VoucherRepository
import javax.inject.Inject

class GetVouchersUseCase @Inject constructor(private val repository: VoucherRepository) {
    suspend operator fun invoke(): Response<List<Voucher>> {
        return try {
            Response.Success(repository.getVouchers())
        } catch (e: BadRequestException) {
            Response.Error("${e.message}")
        } catch (e: InternalErrorException) {
            Response.Error("${e.message}")
        }
    }
}