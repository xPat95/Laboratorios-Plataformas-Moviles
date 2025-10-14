package com.pt.lab11p.ui.location

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable data object GraphLocations
@Serializable data object DestinoLocationsList
@Serializable data class DestinoLocationDetail(val id: Int)

fun RutasLocations(
    builder: NavGraphBuilder,
    nav: NavHostController
) {

    builder.navigation<GraphLocations>(startDestination = DestinoLocationsList) {

        composable<DestinoLocationsList> {
            screenLocations(
                on_click_location = { id ->
                    nav.navigate(DestinoLocationDetail(id))
                }
            )
        }

        composable<DestinoLocationDetail> {
            screenLocationsDetails(
                on_back = { nav.popBackStack() }
            )
        }
    }
}