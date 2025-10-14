package com.pt.lab11p.ui.character

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable data object GraphCharacters
@Serializable data object DestinoCharactersList
@Serializable data class DestinoCharacterDetail(val id: Int)


fun RutasPersonajes(
    builder: NavGraphBuilder,
    nav: NavHostController
) {

    builder.navigation<GraphCharacters>(startDestination = DestinoCharactersList) {

        composable<DestinoCharactersList> {
            screenCharacters(
                on_click_personaje = { id ->
                    nav.navigate(DestinoCharacterDetail(id))
                }
            )
        }

        composable<DestinoCharacterDetail> {
            screenCharacterDetail(
                on_back = { nav.popBackStack() }
            )
        }
    }
}