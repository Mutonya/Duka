package com.maestro.duka.domain.repository

import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.utils.Resource

interface PaymentRepositoryImpl {

    suspend fun getPaymentDetails(): Resource<PaymentResponse>
}