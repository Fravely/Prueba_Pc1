import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun pagPrincipal(navController: NavController) {
    // Column organiza sus hijos verticalmente.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 40.dp, top = 80.dp), // Mueve el contenido un poco hacia abajo y a la izquierda
        horizontalAlignment = Alignment.Start, // Alinea los botones a la izquierda
        verticalArrangement = Arrangement.Top // Coloca los botones arriba
    ) {
        // Primer botón
        Button(
            onClick = {
                navController.navigate("MenuPerrito")
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Calculadora de edad canina", fontSize = 18.sp)
        }

        // Segundo botón
        Button(
            onClick = {
                navController.navigate("MenuDivisas")
            },
            modifier = Modifier
                .padding(top = 32.dp) // Más espacio arriba
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Conversor de divisas", fontSize = 18.sp)
        }

        // Tercer botón
        Button(
            onClick = {
                navController.navigate("MenuProductos")
            },
            modifier = Modifier
                .padding(top = 32.dp) // Más espacio arriba
                .fillMaxWidth(0.8f)
                .height(60.dp)
        ) {
            Text("Catálogo de productos", fontSize = 18.sp)
        }
    }


}
