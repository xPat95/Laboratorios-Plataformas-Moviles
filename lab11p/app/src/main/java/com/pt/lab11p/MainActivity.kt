package com.pt.lab11p

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pt.lab11p.ui.home.DestinoHome
import com.pt.lab11p.ui.home.homeGraph
import com.pt.lab11p.ui.login.DestinoLogin
import com.pt.lab11p.ui.login.loginGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nav = rememberNavController()

                    NavHost(
                        navController = nav,
                        startDestination = DestinoLogin
                    ) {
                        loginGraph(
                            onSuccess = {
                                nav.navigate(DestinoHome) {
                                    popUpTo(0)
                                }
                            }
                        )

                        homeGraph(
                            onCerrarSesion = {
                                nav.navigate(DestinoLogin) {
                                    popUpTo(0)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}