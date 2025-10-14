package com.pt.lab11p.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pt.lab11p.ui.loadings.UiState
import com.pt.lab11p.datos.Character
import com.pt.lab11p.repositorios.CharacterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CharactersViewModel(
    private val repo: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState<List<Character>>(isLoading = true))
    val state = _state.asStateFlow()

    init { loadCharacters() }

    fun loadCharacters() {
        _state.value = UiState(isLoading = true)
        viewModelScope.launch {
            try {

                delay(4000)

                val roll = Random.nextInt(1, 11)
                if (roll % 2 == 0) {
                    val list = repo.getCharacters()
                    _state.value = UiState(isLoading = false, data = list, hasError = false)
                } else {
                    _state.value = UiState(isLoading = false, data = null, hasError = true)
                }
            } catch (_: Exception) {
                _state.value = UiState(isLoading = false, data = null, hasError = true)
            }
        }
    }
}