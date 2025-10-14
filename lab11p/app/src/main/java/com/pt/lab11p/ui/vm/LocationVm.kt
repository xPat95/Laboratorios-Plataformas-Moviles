package com.pt.lab11p.ui.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pt.lab11p.ui.loadings.UiState
import com.pt.lab11p.datos.Location
import com.pt.lab11p.entity.DbProvider
import com.pt.lab11p.repositorios.LocationRepository
import com.pt.lab11p.repositorios.LocationRepositoryRoom
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationsViewModel(
    private val repo: LocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val state = _state.asStateFlow()
    init { loadLocations() }
    fun loadLocations() {
        _state.value = UiState(isLoading = true)
        viewModelScope.launch {
            try {

                delay(4000)

                val list = repo.getLocations()        // Lee desde Room
                _state.value = UiState(isLoading = false, data = list, hasError = false)
            } catch (_: Exception) {
                _state.value = UiState(isLoading = false, data = null, hasError = true)
            }
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = DbProvider.get(context)
                    val repo = LocationRepositoryRoom(db.locationDao())
                    return LocationsViewModel(repo) as T
                }
            }
    }
}