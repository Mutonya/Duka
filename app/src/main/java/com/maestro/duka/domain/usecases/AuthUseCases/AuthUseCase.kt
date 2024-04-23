package com.maestro.duka.domain.usecases.AuthUseCases

import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.domain.repository.AuthRepository
import com.maestro.duka.utils.Resource
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
):AuthUseCaseImpl{
    override suspend fun invoke(login: Login): Resource<AuthResponse> {
        return repository.loginUser(login)
    }
}