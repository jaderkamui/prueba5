package com.jadermunoz.myretail

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.* // Importa los componentes Material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController

@Composable
fun ListaVendedoresScreen(navController: NavHostController) { // Añade navController
    val context = LocalContext.current
    val vendedores = remember { mutableStateListOf<Vendedor>() }

    // Datos de los vendedores (reemplaza con tus datos)
    vendedores.add(Vendedor("Juan Pérez", "Ventas", "https://www.ejemplo.com/foto1.jpg"))
    vendedores.add(Vendedor("María Gómez", "Marketing", "https://www.ejemplo.com/foto2.jpg"))
    vendedores.add(Vendedor("Carlos Rodríguez", "Finanzas", "https://www.ejemplo.com/foto3.jpg"))
    vendedores.add(Vendedor("Ana Sánchez", "Recursos Humanos", "https://www.ejemplo.com/foto4.jpg"))
    vendedores.add(Vendedor("Pedro López", "IT", "https://www.ejemplo.com/foto5.jpg"))

    LazyColumn {
        itemsIndexed(vendedores) { index, vendedor ->
            VendedorItem(vendedor, index, audioId = R.raw.audio_vendedor) // Pasa el ID del audio
        }
    }
}

@Composable
fun VendedorItem(vendedor: Vendedor, index: Int, audioId: Int) { // Recibe el ID del audio
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if (index == 2) {
                    mediaPlayer = MediaPlayer.create(context, audioId) // Usa el ID del audio
                    mediaPlayer?.start()

                    DisposableEffect(mediaPlayer) {
                        onDispose {
                            mediaPlayer?.release()
                            mediaPlayer = null
                        }
                    }
                } else {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(vendedor.foto),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(vendedor.nombre)
                Text(vendedor.area)
            }
        }
    }
}

data class Vendedor(val nombre: String, val area: String, val foto: String)