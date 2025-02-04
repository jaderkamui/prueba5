package com.jadermunoz.myretail

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jadermunoz.myretail.CapturaFotoActivity
import com.jadermunoz.myretail.ListaVendedoresScreen // Importa el Composable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
        composable("list") {
            ListaVendedoresScreen(navController)
        }
        composable("details") {
            DetailsScreen(navController)
        }
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {

}

@Composable
fun MainScreen(navController: NavHostController) {

}

@Composable
fun SplashScreen(navController: NavHostController) { // ... (sin cambios) }

    @Composable
    fun MainScreen(navController: NavHostController) {
        val context = LocalContext.current
        Column {
            Text("Pantalla principal")
            Button(onClick = { navController.navigate("list") }) {
                Text("Ir a la lista de vendedores")
            }
            Button(onClick = {
                val intent = Intent(context, CapturaFotoActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Ir a Captura de Foto")
            }
            Button(onClick = {
                val intent =
                    Intent(context, ListaVendedoresActivity::class.java) // Inicia la Activity
                context.startActivity(intent)
            }) {
                Text("Ir a Lista de Vendedores (Activity)")
            }
        }
    }

    @Composable
    fun ListaVendedoresScreen(navController: NavHostController) {
        // ... (código de la pantalla)
    }

    @Composable
    fun DetailsScreen(navController: NavHostController) {
        // ... (sin cambios) }
    }
}
