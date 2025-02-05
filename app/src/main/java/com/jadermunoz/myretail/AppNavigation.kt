package com.jadermunoz.myretail

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jadermunoz.myretail.activities.GraficoVentasScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController)
        }
        composable("list") {
            ListaVendedoresScreen(navController)
        }
        composable("details") {
            DetailsScreen()
        }
        composable("grafico") {
            GraficoVentasScreen(navController)
        }
    }
}

@Composable
fun DetailsScreen() {
    // Aquí puedes agregar el contenido de la pantalla de detalles
}