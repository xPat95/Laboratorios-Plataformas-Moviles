package com.pablotoledo24355.lab7.datos

import com.pablotoledo24355.lab7.clases.Notificacion
import com.pablotoledo24355.lab7.clases.Tipo
import java.time.LocalDateTime

object NotificationSource {
    fun generateFakeNotifications(): List<Notificacion>{
        val ahora = LocalDateTime.now()

        return (1..50).map { i ->
            val tipo = if (i%2==0) Tipo.INFORMATIVA else Tipo.CAPACITACION

            val titulo = when (tipo) {
                Tipo.INFORMATIVA -> "Nueva Versión Disponible"
                Tipo.CAPACITACION -> "Nueva Capacitación"
            }

            val cuerpo = when (tipo) {
                Tipo.INFORMATIVA -> "La aplicación se actualizó a la versión 1.$i"
                Tipo.CAPACITACION -> "No olvides asistir a la próxima capacitación"
            }

            val fechaHora = LocalDateTime.now()
                .minusDays((i/2).toLong())
                .minusMinutes(((i*7)%60).toLong())

            Notificacion(
                id = i,
                tipo = tipo,
                titulo = titulo,
                cuerpo = cuerpo,
                fechaHora = fechaHora
            )
        }
    }
}