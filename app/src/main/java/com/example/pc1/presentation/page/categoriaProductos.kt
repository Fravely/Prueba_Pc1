package com.example.pc1.presentation.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pc1.data.model.productoModel
import java.text.DecimalFormat

val categoriaProductos = listOf(
    productoModel("Tablet", 3492.58, "Cómputo", "https://images-cdn.ubuy.co.id/64c6221783bbd74f26794919-lenovo-tab-m10-plus-3rd-gen-10-tablet.jpg"),
    productoModel("Refrigeradora", 1500.18, "Electrodomésticos", "https://img.freepik.com/psd-gratis/refrigerador-puerta-francesa-acero-inoxidable-elegante_632498-25861.jpg")
)

@Composable
fun ProductListScreen() {
    val total = categoriaProductos.sumOf { it.precio }
    val decimalFormat = DecimalFormat("#,##0.00")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Lista de productos que ocupa el espacio disponible
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
