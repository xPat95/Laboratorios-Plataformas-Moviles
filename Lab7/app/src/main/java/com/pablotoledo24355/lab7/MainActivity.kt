package com.pablotoledo24355.lab7

import android.app.Notification
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.pablotoledo24355.lab7.clases.Tipo
import com.pablotoledo24355.lab7.ui.NotifsViewModel
import com.pablotoledo24355.lab7.ui.theme.Lab7PabloToledoTheme
import com.pablotoledo24355.lab7.clases.Notificacion
import java.time.LocalDateTime
import java.util.logging.Filter


class MainActivity : ComponentActivity() {
    val viewmodel: NotifsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab7PabloToledoTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                Lab7(viewmodel)
            }
        }
    }
}

@Composable
fun Lab7(viewmodel: NotifsViewModel) {
    val estado = viewmodel.estado

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(20.dp)
        ) {
            Text(
                text = "Notificaciones",
                color = MaterialTheme.colorScheme.onPrimary
        )  }
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = "  Tipos de Notificación"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            FilterChip(
                selected = viewmodel.estado.filtro == Tipo.INFORMATIVA,
                onClick = {
                    viewmodel.filtrar(Tipo.INFORMATIVA)
                },
                label = {
                    Text(
                        text = "✓ Informativa"
                    )
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            FilterChip(
                selected = viewmodel.estado.filtro == Tipo.CAPACITACION,
                onClick = {
                    viewmodel.filtrar(Tipo.CAPACITACION)
                },
                label = {
                    Text(
                        text = "✓ Capacitación"
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        LazyColumn {
            items(
                estado.visibles

            ) { notif: Notificacion ->
                ItemNotificacion(notif)
            }
        }
    }
}

@Composable
fun ItemNotificacion(n: Notificacion) {
    val (container, onContainer, icono) = when (n.tipo){
        Tipo.INFORMATIVA -> Triple(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            Icons.Filled.Notifications
        )
        Tipo.CAPACITACION -> Triple(
            MaterialTheme.colorScheme.tertiaryContainer,
            MaterialTheme.colorScheme.onTertiaryContainer,
            Icons.Filled.DateRange
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(container),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = onContainer
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = n.titulo,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = n.cuerpo,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

