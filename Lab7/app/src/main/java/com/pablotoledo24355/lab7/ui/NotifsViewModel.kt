package com.pablotoledo24355.lab7.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pablotoledo24355.lab7.clases.Tipo
import com.pablotoledo24355.lab7.datos.NotificationSource

class NotifsViewModel: ViewModel() {

    var estado by mutableStateOf(
        EstadoNotifs(
            todas = NotificationSource.generateFakeNotifications(),
            filtro = null
        )
    )

    fun filtrar(tipo: Tipo) {
        estado =
            if (estado.filtro == tipo) {
                estado.copy(filtro = null)
            } else {
                estado.copy(filtro = tipo)
            }
    }
}