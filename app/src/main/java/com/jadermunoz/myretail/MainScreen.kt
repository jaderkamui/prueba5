package com.jadermunoz.myretail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp), // Añadir padding superior
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centrar verticalmente
    ) {
        Text("Pantalla principal")
        Spacer(modifier = Modifier.height(16.dp)) // Añadir espacio entre el texto y el primer botón
        Button(onClick = { navController.navigate("list") }) {
            Text("Ir a la lista de vendedores")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Añadir espacio entre botones
        Button(onClick = { navController.navigate("grafico") }) {
            Text("Ir a Gráfico de Ventas")
        }
    }
}