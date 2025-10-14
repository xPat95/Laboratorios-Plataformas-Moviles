package com.pt.lab11p.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pt.lab11p.perfil.DestinoProfile
import com.pt.lab11p.perfil.RegistrarProfile
import com.pt.lab11p.ui.character.GraphCharacters
import com.pt.lab11p.ui.character.RutasPersonajes
import com.pt.lab11p.ui.location.GraphLocations
import com.pt.lab11p.ui.location.RutasLocations
import kotlinx.serialization.Serializable

@Serializable
data object DestinoTabCharacters
@Serializable
data object DestinoTabLocations
@Serializable
data object DestinoTabProfile

private data class ItemBottom(
    val label: String,
    val icon: ImageVector,
    val route: Any
)

@Composable
fun screenHome(
    cerrar_sesion: () -> Unit
) {
    val navBottom = rememberNavController()
    val items = listOf(
        ItemBottom("Characters", Icons.Filled.Person,        DestinoTabCharacters),
        ItemBottom("Locations",  Icons.Filled.LocationOn,    DestinoTabLocations),
        ItemBottom("Profile",    Icons.Filled.AccountCircle, DestinoTabProfile),
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.95f)) {
                val backEntry by navBottom.currentBackStackEntryAsState()
                val current = backEntry?.destination?.route
                items.forEach { item ->
                    val selected = current == item.route::class.qualifiedName
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navBottom.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(DestinoTabCharacters) { saveState = true }
                            }
                        },
                        icon  = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->

        BarraNavegacion(
            navBottom       = navBottom,
            paddingValues   = padding,
            cerrar_sesion = cerrar_sesion
        )
    }
}

@Composable
private fun BarraNavegacion(
    navBottom: NavHostController,
    paddingValues: PaddingValues,
    cerrar_sesion: () -> Unit
) {

    NavHost(
        navController = navBottom,
        startDestination = DestinoTabCharacters,
        modifier = Modifier.padding(paddingValues)
    ) {
        navigation<DestinoTabCharacters>(startDestination = GraphCharacters) {
            RutasPersonajes(
                builder = this,
                nav = navBottom
            )
        }

        navigation<DestinoTabLocations>(startDestination = GraphLocations) {
            RutasLocations(
                builder = this,
                nav = navBottom
            )
        }

        navigation<DestinoTabProfile>(startDestination = DestinoProfile) {
            RegistrarProfile(
                builder = this,
                cerrar_sesion = cerrar_sesion
            )
        }
    }
}