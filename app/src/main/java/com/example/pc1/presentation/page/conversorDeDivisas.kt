package com.example.pc1.presentation.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConverter(navController: NavController) {
    var amountInput by remember { mutableStateOf("") }
    val conversionOptions = listOf("USD a PEN", "PEN a USD")
    var selectedOption by remember { mutableStateOf(conversionOptions[0]) }
    var conversionResult by remember { mutableStateOf<String?>(null) }
    var isAmountError by remember { mutableStateOf(false) }

    val exchangeRate = 3.80
    val decimalFormat = DecimalFormat("#,##0.00")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Volver") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Conversor de divisas",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Input para el monto
                OutlinedTextField(
                    value = amountInput,
                    onValueChange = {
                        amountInput = it
                        isAmountError = false
                    },
                    label = { Text("Monto a convertir") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    isError = isAmountError,
                    supportingText = {
                        if (isAmountError) {
                            Text("Por favor, ingresa un monto válido.")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // RadioButtons para tipo de conversión
                Text("Tipo de Conversión:", style = MaterialTheme.typography.bodyLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    conversionOptions.forEach { option ->
                        Row(
                            Modifier
                                .selectable(
                                    selected = (option == selectedOption),
                                    onClick = { selectedOption = option }
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == selectedOption),
                                onClick = null // Click se maneja en Row
                            )
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                // Botón de cálculo
                Button(onClick = {
                    val amount = amountInput.toDoubleOrNull()
                    if (amount == null || amount <= 0) {
                        isAmountError = true
                        conversionResult = null
                    } else {
                        isAmountError = false
                        val result = if (selectedOption == "USD a PEN") {
                            val converted = amount * exchangeRate
                            "S/ ${decimalFormat.format(converted)}"
                        } else {
                            val converted = amount / exchangeRate
                            "$ ${decimalFormat.format(converted)}"
                        }
                        conversionResult = "Resultado: $result"
                    }
                }) {
                    Text("Convertir")
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Resultado
                conversionResult?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}
