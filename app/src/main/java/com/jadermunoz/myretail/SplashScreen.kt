package com.jaderkamui.prueba5

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    Column {
        Text("Pantalla de presentación")
        Button(onClick = { navController.navigate("main") }) {
            Text("Ir a la pantalla principal")
        }
    }
}