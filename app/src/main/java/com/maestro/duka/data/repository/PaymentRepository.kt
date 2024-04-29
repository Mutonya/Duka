package com.maestro.duka.data.repository

import com.maestro.duka.data.remote.FakeStoreApi
import com.maestro.duka.data.remote.StripeApi
import com.maestro.duka.data.remote.dto.PaymentResponse
import com.maestro.duka.domain.repository.PaymentRepositoryImpl
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val fakeStoreApi: StripeApi
): PaymentRepositoryImpl {
    override suspend fun getPaymentDetails(): Resource<PaymentResponse> {
        val response = try {
            val response = fakeStoreApi.getPaymentInfofromApi()
            Resource.Success(response)

        }catch (e:Exception){
            Resource.Error(e.message!!)

        }
        return response
    }

}