package com.jadermunoz.myretail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.layout.ContentScale

@Composable
fun SellerListScreen() {
    val sellers = listOf( // Lista inmutable
        Seller("Juan Pérez", "Ventas", "https://www.ejemplo.com/foto1.jpg"),
        Seller("María Gómez", "Marketing", "https://www.ejemplo.com/foto2.jpg"),
        Seller("Carlos Rodríguez", "Finanzas", "https://www.ejemplo.com/foto3.jpg"),
        Seller("Ana Sánchez", "Recursos Humanos", "https://www.ejemplo.com/foto4.jpg"),
        Seller("Pedro López", "IT", "https://www.ejemplo.com/foto5.jpg")
    )

    LazyColumn {
        items(sellers) { seller ->
            SellerItem(seller)
        }
    }
}

@Composable
fun SellerItem(seller: Seller) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(seller.foto),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(seller.nombre)
                Text(seller.area)
            }
        }
    }
}

data class Seller(val nombre: String, val area: String, val foto: String) // Clase Seller