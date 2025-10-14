package com.pt.lab11p.repositorios

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    val userName: Flow<String?>
    suspend fun login(name: String)
    suspend fun logout()
}