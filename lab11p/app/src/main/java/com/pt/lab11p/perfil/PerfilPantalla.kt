package com.pt.lab11p.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pt.lab11p.ui.vm.ProfileViewModel

@Composable
fun screenProfile(
    onCerrarSesion: () -> Unit,
    vm: ProfileViewModel = viewModel(
        factory = ProfileViewModel.provideFactory(LocalContext.current)
    )
) {
    val name by vm.userName.collectAsStateWithLifecycle()

    pantallaProfile(
        nombre = name ?: "Invitado",
        carne = "24355",
        on_cerrar_sesion = {
            vm.logout()
            onCerrarSesion()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaProfile(
    nombre: String,
    carne: String,
    on_cerrar_sesion: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                model = "https://i.pinimg.com/736x/d3/17/c8/d317c80b6e4eec4fe782e680e01b723f.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Nombre:", fontWeight = FontWeight.SemiBold)
                Text(nombre, fontSize = 18.sp)

                Spacer(Modifier.height(8.dp))

                Text("Carné:", fontWeight = FontWeight.SemiBold)
                Text(carne, fontSize = 18.sp)
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = on_cerrar_sesion,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}