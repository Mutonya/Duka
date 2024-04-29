package com.maestro.duka.domain.usecases.Payments

import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.data.repository.PaymentRepository
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class PaymentUseCase @Inject constructor(
    private val repository:PaymentRepository
):PaymentsUseCaseImpl {
    override suspend fun invoke(): Resource<PaymentResponse> {
        return repository.getPaymentDetails()
    }
}