package com.pablotoledo.lab9pablotoledo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.net.URLDecoder

@Serializable
data class Lugar(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val dimension: String
)

class LugarDb {

    private val lugares: List<Lugar> = listOf(
        Lugar(1, "Earth (C-137)", "Planeta", "Dimension C-137"),
        Lugar(2, "Abadango", "Clúster", "unknown"),
        Lugar(3, "Citadel of Ricks", "Estación espacial", "unknown"),
        Lugar(4, "Worldender's lair", "Planeta", "unknown"),
        Lugar(5, "Anatomy Park", "Microverso", "Dimension C-137"),
        Lugar(6, "Interdimensional Cable", "TV", "unknown"),
        Lugar(7, "Immortality Field Resort", "Resort", "unknown"),
        Lugar(8, "Post-Apocalyptic Earth", "Planeta", "Post-Apocalyptic Dimension"),
        Lugar(9, "Purge Planet", "Planeta", "Replacement Dimension"),
        Lugar(10, "Venzenulon 7", "Planeta", "unknown"),
        Lugar(11, "Bepis 9", "Planeta", "unknown"),
        Lugar(12, "Cronenberg Earth", "Planeta", "Cronenberg Dimension"),
        Lugar(13, "Nuptia 4", "Planeta", "unknown"),
        Lugar(14, "Giant's Town", "Pueblo fantástico", "Fantasy Dimension"),
        Lugar(15, "Bird World", "Planeta", "unknown"),
        Lugar(16, "St. Gloopy Noops Hospital", "Estación espacial", "unknown"),
        Lugar(17, "Earth (5-126)", "Planeta", "Dimension 5-126"),
        Lugar(18, "Mr. Goldenfold's dream", "Sueño", "Dimension C-137"),
        Lugar(19, "Gromflom Prime", "Planeta", "Replacement Dimension"),
        Lugar(20, "Earth (Replacement Dimension)", "Planeta", "Replacement Dimension")
    )

    fun getTodosLosLugares(): List<Lugar> = lugares
}

object LugaresRepository {
    private val db = LugarDb()
    fun getLugares(): List<Lugar> = db.getTodosLosLugares()
}

@Composable
fun Lugares() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lugares") {

        composable("lugares") {
            LugaresScreen(
                lugares = LugaresRepository.getLugares(),
                onLugarClick = { lugar ->
                    val json = Json.encodeToString(Lugar.serializer(), lugar)
                    val encoded = URLEncoder.encode(json, "UTF-8")
                    navController.navigate("lugar/$encoded")
                }
            )
        }

        composable(
            route = "lugar/{lugarArg}",
            arguments = listOf(navArgument("lugarArg") { type = NavType.StringType })
        ) { backStack ->
            val encoded = backStack.arguments?.getString("lugarArg") ?: return@composable
            val json = URLDecoder.decode(encoded, "UTF-8")
            val lugar = Json.decodeFromString(Lugar.serializer(), json)

            LugarDetalle(lugar = lugar, onBack = { navController.popBackStack() })
        }
    }
}

@Composable
private fun LugaresScreen(
    lugares: List<Lugar>,
    onLugarClick: (Lugar) -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        TopBar(title = "Lugares")

        LazyColumn(Modifier.fillMaxSize()) {
            items(lugares) { lugar ->
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onLugarClick(lugar) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(lugar.nombre, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Spacer(Modifier.height(2.dp))
                    Text("${lugar.tipo} · ${lugar.dimension}", fontSize = 13.sp, color = Color.Gray)
                }
                Divider()
            }
        }
    }
}

@Composable
private fun LugarDetalle(lugar: Lugar, onBack: () -> Unit) {

    Column(Modifier.fillMaxSize()) {

        TopBar(title = "Detalle del Lugar", showBack = true, onBack = onBack)

        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(lugar.nombre, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.height(24.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Tipo:")
                Text(lugar.tipo, fontWeight = FontWeight.Medium)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Dimensión:")
                Text(lugar.dimension, fontWeight = FontWeight.Medium)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ID:")
                Text(lugar.id.toString(), fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
private fun TopBar(
    title: String,
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF27CFF5))
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBack && onBack != null) {
            Spacer(Modifier.width(12.dp))
            Text(
                text = "←",
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(end = 8.dp)
            )
        } else {
            Spacer(Modifier.width(20.dp))
        }
        Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
