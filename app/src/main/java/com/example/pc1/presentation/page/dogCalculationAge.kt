package com.example.pc1.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogAgeCalculator(navController: NavController) {
    var humanAgeInput by remember { mutableStateOf("") }
    var selectedSize by remember { mutableStateOf("Pequeño") }
    var dogAgeResult by remember { mutableStateOf<String?>(null) }
    var isAgeError by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val dogSizes = listOf("Pequeño", "Mediano", "Grande")

    // Fondo con color suave
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Tarjeta con sombra y esquinas redondeadas
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Título
                Text(
                    "🐾 Calculadora de Edad Canina",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                // Campo para ingresar la edad
                OutlinedTextField(
                    value = humanAgeInput,
                    onValueChange = {
                        humanAgeInput = it
                        isAgeError = false
                    },
                    label = { Text("Edad de tu perro (en años humanos)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = isAgeError,
                    supportingText = {
                        if (isAgeError) {
                            Text("Por favor, ingresa un número positivo.")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // Dropdown para el tamaño
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedSize,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Tamaño del perro") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        dogSizes.forEach { size ->
                            DropdownMenuItem(
                                text = { Text(size) },
                                onClick = {
                                    selectedSize = size
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Botón para calcular
                Button(
                    onClick = {
                        val age = humanAgeInput.toIntOrNull()
                        if (age == null || age <= 0) {
                            isAgeError = true
                            dogAgeResult = null
                        } else {
                            isAgeError = false
                            val multiplier = when (selectedSize) {
                                "Pequeño" -> 5
                                "Mediano" -> 6
                                else -> 7
                            }
                            val result = age * multiplier
                            dogAgeResult =
                                "🐶 Tu perro tiene $result años perrunos. ¡GUAAAAF!"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Calcular Edad", fontSize = 18.sp)
                }

                // Resultado
                dogAgeResult?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

