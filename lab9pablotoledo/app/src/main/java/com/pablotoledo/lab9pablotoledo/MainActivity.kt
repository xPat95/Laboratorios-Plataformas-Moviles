package com.pablotoledo.lab9pablotoledo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lab9Pablo()
        }
    }
}

@Composable
fun lab9Pablo() {

    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf("Characters", "Locations", "Profile")

    val icons = listOf(
        //Imagen de characters
        "https://cdn-icons-png.flaticon.com/512/3537/3537845.png",
        //Imagen de locations
        "https://w7.pngwing.com/pngs/213/871/png-transparent-location-logo-map-location-icon-road-map-black-symbol-thumbnail.png",
        //Imagen de profile
        "https://e7.pngegg.com/pngimages/993/650/png-clipart-user-profile-computer-icons-others-miscellaneous-black.png"

    )

    //Estructura visual de la pantalla.
    Scaffold(
        //Barra de navegacion inferior.
        bottomBar = {
            NavigationBar {
                //Se asigna la lista junto a los nombres que van en la barra de navegacion inferior.
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        //Asignacion de iconos por medio de AsyncImage para cada nombre.
                        icon = {
                            AsyncImage(
                                model = icons[index],
                                contentDescription = null,
                                Modifier.size(24.dp)
                            )
                        },
                        //Label para mostrar el texto debajo de los iconos.
                        label = { Text(item) },
                        //Para indicar el item selecciona por el usuario.
                        selected = selectedItem == index,
                        //Accion que hara el selectedItem.
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) {
        innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            when (selectedItem) {
                0 -> Personajes()
                1 -> Lugares()
                2 -> Profile()
            }
        }
    }
}