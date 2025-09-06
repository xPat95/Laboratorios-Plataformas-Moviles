package com.pablotoledo24355.lab7.clases

import android.app.Notification
import java.time.LocalDateTime

data class Notificacion(
    val id: Int,
    val titulo: String,
    val cuerpo: String,
    val tipo: Tipo,
    val fechaHora: LocalDateTime
)