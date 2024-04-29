package com.maestro.duka.domain.usecases.Payments

import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.utils.Resource

interface PaymentsUseCaseImpl {
    suspend operator fun invoke(): Resource<PaymentResponse>

}