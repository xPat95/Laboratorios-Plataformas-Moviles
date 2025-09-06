package com.pablotoledo24355.lab7.ui

import com.pablotoledo24355.lab7.clases.Notificacion
import com.pablotoledo24355.lab7.clases.Tipo

data class EstadoNotifs(
    val todas: List<Notificacion> = emptyList(),
    val filtro: Tipo? = null
){
    val visibles: List<Notificacion>
        get() = if (filtro == null){
            todas
        } else{
            todas.filter { it.tipo == filtro }
        }
}