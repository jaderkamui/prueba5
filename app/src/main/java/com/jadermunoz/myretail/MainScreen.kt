package com.jadermunoz.myretail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("sellerList") }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Lista de Vendedores")
        }
        Button(onClick = { navController.navigate("camera") }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Capturar Foto")
        }
        Button(onClick = { navController.navigate("salesChart") }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Gráfico de Ventas")
        }
    }
}
