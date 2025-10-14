package com.pt.lab11p.repositorios

import com.pt.lab11p.ui.PrefsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val prefs: PrefsDataStore
) : AuthRepository {

    override val userName: Flow<String?> = prefs.name

    override val isLoggedIn: Flow<Boolean> = prefs.name.map { !it.isNullOrBlank() }

    override suspend fun login(name: String) {
        prefs.saveName(name)
    }

    override suspend fun logout() {
        prefs.clearName()
    }
}