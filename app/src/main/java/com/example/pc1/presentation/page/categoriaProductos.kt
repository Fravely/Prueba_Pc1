package com.example.pc1.presentation.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pc1.data.model.productoModel
import java.text.DecimalFormat

val categoriaProductos = listOf(
    productoModel("Tablet", 3492.58, "Cómputo", "https://images-cdn.ubuy.co.id/64c6221783bbd74f26794919-lenovo-tab-m10-plus-3rd-gen-10-tablet.jpg"),
    productoModel("Refrigeradora", 1500.18, "Electrodomésticos", "https://img.freepik.com/psd-gratis/refrigerador-puerta-francesa-acero-inoxidable-elegante_632498-25861.jpg"),
    productoModel("Mouse", 85.78, "Gamer", "https://w7.pngwing.com/pngs/87/604/png-transparent-computer-mouse-gamer-mouse-mats-mad-catz-r-a-t-1-computer-mouse-electronics-computer-mouse.png"),
    productoModel("Escritorio", 3492.58, "Mueble", "https://e7.pngegg.com/pngimages/425/899/png-clipart-brown-wooden-pedestal-desk-coffee-table-desk-wood-office-solid-wood-office-desk-angle-furniture-thumbnail.png"),
    productoModel("Lavadora", 3492.58, "Electrodomésticos", "https://blog.mabeglobal.com/wp-content/uploads/2024/01/como-lavar-ropa-lavarropas.jpg")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavController) {
    val total = categoriaProductos.sumOf { it.precio }
    val decimalFormat = DecimalFormat("#,##0.00")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Volver") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Lista de productos
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(categoriaProductos) { product ->
                        ProductCard(product)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Total acumulado alineado al final
                Text(
                    text = "Total Acumulado: S/ ${decimalFormat.format(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    )
}


@Composable
fun ProductCard(product: productoModel) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            // Imagen del producto
            Image(
                painter = rememberAsyncImagePainter(product.imagenUrl),
                contentDescription = product.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            // Detalles del producto
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = product.nombre, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Categoría: ${product.categoria}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "S/ ${DecimalFormat("#,##0.00").format(product.precio)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
