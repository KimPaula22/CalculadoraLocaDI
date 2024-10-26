package com.example.calculadoradi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradi.CalculadoraLoca

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraScreen()
        }
    }
}
@Composable
fun CalculadoraScreen() {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val calculadoraLoca = CalculadoraLoca()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Fondo negro
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp).fillMaxHeight(0.8f) // Ocupa el 80% de la altura
        ) {
            // Campo de expresión (recuadro azul)
            OutlinedTextField(
                value = expression,
                onValueChange = {}, // Solo lectura
                label = { Text("Expresión") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFADD8E6)), // Fondo azul para el campo de texto
                textStyle = LocalTextStyle.current.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Recuadro de resultado (en rosa)
            OutlinedTextField(
                value = result,
                onValueChange = {}, // Solo lectura
                label = { Text("Resultado") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFFFC0CB)), // Fondo rosa para el campo de texto
                textStyle = LocalTextStyle.current.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Teclado de la calculadora
            val botones = listOf(
                listOf("7", "8", "9", "&"),
                listOf("4", "X", "6", "@"),
                listOf("1", "2", "3", "#"),
                listOf("C", "0", "=", "$")
            )

            botones.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    row.forEach { text ->
                        Button(
                            onClick = {
                                when (text) {
                                    "=" -> {
                                        // Calcular el resultado usando la clase CalculadoraLoca
                                        result = calculadoraLoca.calcular(expression)
                                    }
                                    "C" -> {
                                        expression = ""
                                        result = ""
                                    }
                                    else -> expression += calculadoraLoca.obtenerValor(text)
                                }
                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp)), // Bordes redondeados
                            colors = ButtonDefaults.buttonColors(containerColor = when (text) {
                                "0", "2", "4", "6", "8" -> Color(0xFFFFC0CB) // Rosa pastel
                                "1", "3", "5", "7", "9" -> Color(0xFFADD8E6) // Azul claro
                                else -> Color(0xFFE6E6FA) // Color por defecto
                            })
                        ) {
                            Text(
                                text = text,
                                fontSize = 20.sp,
                                color = Color.Black // Texto en blanco
                            )
                        }
                    }
                }
            }
        }

        // Añadir íconos en las esquinas
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_star),
                contentDescription = "Estrella",
                modifier = Modifier.size(82.dp).padding(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_candy),
                contentDescription = "Caramelo",
                modifier = Modifier.size(82.dp).padding(16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_hearts),
                contentDescription = "Brillito",
                modifier = Modifier.size(82.dp).padding(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_ic),
                contentDescription = "ice cream",
                modifier = Modifier.size(82.dp).padding(16.dp)
            )
        }
    }
}