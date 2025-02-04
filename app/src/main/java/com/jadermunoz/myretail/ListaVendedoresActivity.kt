package com.jadermunoz.myretail // Reemplaza con tu paquete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jadermunoz.myretail.ListaVendedoresScreen // Importa el Composable

class ListaVendedoresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Inicializa el NavController
            ListaVendedoresScreen(navController) // Llama al Composable
        }
    }
}