package com.pablotoledo24355.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pablotoledo24355.lab8.ui.screens.CharacterDetailScreen
import com.pablotoledo24355.lab8.ui.screens.CharacterScreen
import com.pablotoledo24355.lab8.ui.screens.LoginScreen
import com.pablotoledo24355.lab8.ui.theme.Lab8Theme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val nav = rememberNavController()

                NavHost(
                    navController = nav,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(
                            onLogin = {
                                nav.navigate("characters"){
                                    popUpTo("login"){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("characters") {
                        CharacterScreen(
                            onCharacterClick = {
                                id -> nav.navigate("detail/$id")
                            }
                        )
                    }
                    composable(
                        route = "detail/{id}",
                        arguments = listOf(navArgument("id"){type = NavType.IntType})
                    ) {
                        backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id") ?: 0
                        CharacterDetailScreen(
                            id = id,
                            onBack = {nav.popBackStack()}
                        )
                    }
                }
            }
        }
    }
}