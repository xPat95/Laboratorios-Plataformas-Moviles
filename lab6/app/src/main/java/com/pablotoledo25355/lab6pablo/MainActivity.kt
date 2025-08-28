package com.pablotoledo25355.lab6pablo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lab6p()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun lab6p() {
    var cuenta by rememberSaveable { mutableStateOf(0) }
    var aumentos by rememberSaveable { mutableStateOf(0) }
    var disminuciones by rememberSaveable { mutableStateOf(0) }
    var maximo by rememberSaveable { mutableStateOf(0) }
    var minimo by rememberSaveable { mutableStateOf(0) }
    val cambio = aumentos + disminuciones
    val verde = Color(0xFF4CAF50)
    val rojo = Color(0xFFF44336)

    val historial = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { it.toMutableStateList() }
        )
    ) { mutableStateListOf<Int>() }

    val historialaumento = rememberSaveable(
        saver = listSaver(
            save = { list -> list.map { if (it) 1 else 0 } },
            restore = { list -> list.map { it != 0 }.toMutableStateList() }
        )
    ) { mutableStateListOf<Boolean>() }

    val historialcambio = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { it.toMutableStateList() }
        )
    ) { mutableStateListOf<Int>() }

    fun aumentar() {
        cuenta++
        aumentos++
        if (cuenta > maximo) maximo = cuenta
        historial.add(cuenta)
        historialaumento.add(true)
    }

    fun disminuir() {
        cuenta--
        disminuciones++
        if (cuenta < minimo) minimo = cuenta
        historial.add(cuenta)
        historialaumento.add(false)
    }

    fun Reset() {
        cuenta = 0
        aumentos = 0
        disminuciones = 0
        maximo = 0
        minimo = 0
        historial.clear()
        historialaumento.clear()
    }

    Column (
        Modifier.fillMaxSize()
    ) {

        Spacer(Modifier.height(20.dp))

        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Pablo Toledo",
                fontSize = 20.sp,
            )
        }

        Spacer(Modifier.height(70.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    disminuir()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF0000)
                )
            ) {
                Text(
                    text = "-",
                    fontSize = 30.sp
                )
            }

            Text(
                text = "$cuenta",
                fontSize = 100.sp

            )

            Button(
                onClick = {
                    aumentar()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text (
                    text = "+",
                    fontSize = 30.sp
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Total incrementos: $aumentos")
            Text("Total decrementos: $disminuciones")
            Text("Valor maximo: $maximo")
            Text("Valor minimo: $minimo")
            Text("Total cambios: $cambio")
        }


        Spacer(Modifier.height(10.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Historial: ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        HistorialFlowNumeros(
            valores = historial,
            fueIncremento = historialaumento,
            colorInc = verde,
            colorDec = rojo
        )

        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){

            Button(
                //Llama a la funcion de Decrementar.
                onClick = {
                    Reset()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9700FF)
                )
            ) {
                Text(
                    text = "Reiniciar",
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HistorialFlowNumeros(
    valores: List<Int>,
    fueIncremento: List<Boolean>,
    colorInc: Color,
    colorDec: Color
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        maxItemsInEachRow = 5,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (i in valores.indices) {
            val bg = if (fueIncremento[i]) colorInc else colorDec
            Registro(valor = valores[i], bg = bg)
        }
    }
}

@Composable
fun Registro(valor: Int, bg: Color) {
    Surface(
        color = bg,
        contentColor = Color.White,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 8.dp)
                .defaultMinSize(minWidth = 36.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = valor.toString(), fontSize = 18.sp)
        }
    }
}