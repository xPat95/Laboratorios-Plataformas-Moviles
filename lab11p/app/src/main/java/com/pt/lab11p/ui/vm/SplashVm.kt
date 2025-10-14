package com.pt.lab11p.ui.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pt.lab11p.ui.PrefsDataStore
import com.pt.lab11p.repositorios.AuthRepository
import com.pt.lab11p.repositorios.AuthRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(
    private val authRepo: AuthRepository
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean?> =
        authRepo.isLoggedIn
            .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val prefs = PrefsDataStore(context)
                    val repo: AuthRepository = AuthRepositoryImpl(prefs)
                    return SplashViewModel(repo) as T
                }
            }
    }
}