package com.maestro.duka.domain.usecases.AuthUseCases

import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.utils.Resource

interface AuthUseCaseImpl {

    suspend operator fun invoke(login: Login):Resource<AuthResponse>
}