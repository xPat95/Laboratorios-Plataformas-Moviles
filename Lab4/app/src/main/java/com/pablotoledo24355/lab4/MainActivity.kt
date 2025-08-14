package com.pablotoledo24355.lab4

import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lab4() {
    Box(
        Modifier.fillMaxSize()
            .border(
                10.dp, Color(0xFF2F4538), RectangleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondouvg),
            contentDescription = null,
            modifier = Modifier.width(350.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )

        Column(
            modifier = Modifier.align(Alignment.Center)
                .widthIn(500.dp)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Universidad del Valle de Guatemala", fontSize = 35.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Text("Programación de Plataformas Móviles, Sección 30", fontSize = 25.sp, textAlign = TextAlign.Center)
            Row(
                modifier = Modifier.width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Integrantes", fontWeight = FontWeight.Bold)
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Pablo Toledo")
                        Text("Luis Lee")
                        Text("Adriana Martinez")
                    }
                }
            }
            Row(
                modifier = Modifier.width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Catedrático", fontWeight = FontWeight.Bold)
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Juan Carlos Durini")
                    }
                     }

            }
            Box() {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Pablo Toledo", fontSize = 20.sp)
                    Text("Carné #24355", fontSize = 20.sp)
                }
            }
        }
    }
}
