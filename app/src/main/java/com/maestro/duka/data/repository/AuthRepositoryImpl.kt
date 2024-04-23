package com.maestro.duka.data.repository

import android.util.Log
import com.maestro.duka.data.remote.FakeStoreApi
import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.domain.repository.AuthRepository
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl  @Inject constructor(
    private val fakeStoreApi: FakeStoreApi
):AuthRepository{
    override suspend fun loginUser(login: Login): Resource<AuthResponse> {
        val response = try {
            val response = fakeStoreApi.loginUser(login)
            Resource.Success(response)

        }catch (e:Exception){
            Resource.Error(e.message!!,null)

        }


        return response
    }
}