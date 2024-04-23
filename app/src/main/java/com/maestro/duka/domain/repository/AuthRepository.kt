package com.maestro.duka.domain.repository

import com.maestro.duka.data.remote.dto.AuthResponse
import com.maestro.duka.data.remote.dto.Login
import com.maestro.duka.utils.Resource

interface AuthRepository {


    suspend fun loginUser(login: Login):Resource<AuthResponse>
}