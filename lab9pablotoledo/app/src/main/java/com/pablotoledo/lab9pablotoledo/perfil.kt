package com.pablotoledo.lab9pablotoledo

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Profile() {

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://fcb-abj-pre.s3.amazonaws.com/img/jugadors/MESSI.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
            )

            Text(
                text = "Nombre: Pablo Toledo"
            )

            Text(
                text = "Carnet: 25355"
            )

            Spacer(Modifier.height(100.dp))

            val context = LocalContext.current
            val activity = context as? Activity
            Button (
                onClick = {
                    activity?.finishAffinity()
                },

            ) {
                Text(
                    "Cerrar sesion"
                )
            }
        }
    }
}