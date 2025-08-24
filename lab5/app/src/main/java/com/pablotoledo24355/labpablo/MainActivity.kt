package com.pablotoledo24355.labpablo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lab5()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun lab5() {

    Column(
        Modifier.fillMaxSize()
    ) {

        //Fila del boton historial, Actualizacion disponible y boton de descargar
        Row(
            Modifier.fillMaxWidth()
                .background(Color(0xFFC8E4F7))
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Boton que no hace nada, pero es el logo del historial
            IconButton(
                onClick = {},
                Modifier.background(Color(0xFF27C5F5), CircleShape)
                        .size(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.boton1),
                    contentDescription = null,
                    Modifier.size(20.dp)
                )
            }
            //Texto de actualizacion disponible
            Text(
                text = "Actualizacion Disponible",
            )

            //variable local y Intent para acceder al url de la aplicacion.
            val aplicacion = LocalContext.current
            TextButton(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://play.google.com/store/apps/details?id=jp.pokemon.pokemontcgp&hl=es_GT".toUri()
                    )
                    aplicacion.startActivity(intent)
                }
            ) {
                Text(
                    text = "Descarga",
                    color = Color(0xFF27C5F5)
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        //Columna de la fecha de cumpleanios y terminar jornada
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Columna de los datos de mi cumple
            Column {
                Text(
                    text = "Sabado",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "10 de Mayo",
                    fontSize = 17.sp,
                    color = Color(0xFF717F8A)
                )
            }

            //Terminar jornada
            OutlinedButton(
                onClick = {},

            ) {
                Text(
                    text = "Terminar jornada",
                    color = Color(0xFF9F27F5)
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        //Dibuja rombo
        val direccion = androidx.compose.foundation.shape.GenericShape { size, _ ->
            moveTo(size.width / 2, 0f)
            lineTo(size.width, size.height / 2)
            lineTo(size.width / 2, size.height)
            lineTo(0f, size.height / 2)
            close()
        }

        //Almacenar Card
        Box(
            Modifier.fillMaxWidth()
                .padding(16.dp)
        ){
            //Card para guardar todo el contenido dentro
            Card (
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                ) {
                    //Fila para el nombre del restaurante y el boton de direccion
                    Row() {
                        Text(
                            text = "Mongolia BBQ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Spacer(Modifier.width(180.dp))

                        val restaurante = LocalContext.current
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW,
                                    "https://maps.app.goo.gl/gjuduP2iPuUX8pDp6".toUri())
                                restaurante.startActivity(intent)
                            },

                            Modifier
                                .size(20.dp)
                                .background(Color(0xFFDBABFF), direccion)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.boton2),
                                contentDescription = null,
                                Modifier.size(40.dp)
                            )
                        }
                    }

                    Text(
                        text = "Avenida Las Americas",
                        color = Color(0xFF717F8A)
                    )

                    Text(
                        text = "12 p.m a 4 p.m",
                        color = Color(0xFF717F8A)
                    )

                    Spacer(Modifier.height(40.dp))

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        val nombre = LocalContext.current
                        Button(
                            onClick = {
                                Toast.makeText(nombre, "Pablo Toledo", Toast.LENGTH_SHORT).show()
                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD48787)
                            ),

                            modifier = Modifier
                                .height(40.dp)
                                .width(100.dp)
                        ){
                            Text(
                                text = "Iniciar",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }

                        val comida = LocalContext.current
                        TextButton(
                            onClick = {
                                Toast.makeText(comida, "Comida taiwanesa \n precio: Q100", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(
                                text = "Detalles",
                                color = Color(0xFFD48787)
                            )
                        }

                    }
                }

            }
        }
    }
}

