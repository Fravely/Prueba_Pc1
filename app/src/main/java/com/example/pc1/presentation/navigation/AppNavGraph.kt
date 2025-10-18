package com.example.pc1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pc1.presentation.page.CurrencyConverter
import com.example.pc1.presentation.page.DogAgeCalculator
import com.example.pc1.presentation.page.ProductListScreen
import pagPrincipal

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            pagPrincipal(navController)
        }
        composable("MenuPerrito"){
            DogAgeCalculator(navController)
        }
        composable("MenuDivisas"){
            CurrencyConverter(navController)
        }
        composable("MenuProductos"){
            ProductListScreen(navController)
        }

    }

}