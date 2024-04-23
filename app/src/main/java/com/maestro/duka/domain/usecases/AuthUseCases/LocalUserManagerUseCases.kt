package com.maestro.duka.domain.usecases.AuthUseCases

import com.maestro.duka.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class LocalUserManagerUseCases  (
    private val localUserManager: LocalUserManager
){

    suspend fun saveAppEntry(){
        localUserManager.saveAppEntry()
    }

    suspend fun readAppEntry(): Flow<Boolean> {
       return localUserManager.readAppEntry()
    }

    suspend fun saveToken(token:String){
        localUserManager.saveToken(token)
    }

    suspend fun retrieveToken():Flow<String>{
        return localUserManager.readToken()
    }
}