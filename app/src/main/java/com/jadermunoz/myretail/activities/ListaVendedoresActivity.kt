package com.jaderkamui.prueba5

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.Image
import androidx.compose.ui.layout.ContentScale

class ListaVendedoresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaVendedoresScreen()
        }
    }
}

@Composable
fun ListaVendedoresScreen() {
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
            VendedorItem(vendedor, index)
        }
    }
}

@Composable
fun VendedorItem(vendedor: Vendedor, index: Int) {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? = null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if (index == 2) { // Tercer vendedor (índice 2)
                    mediaPlayer = MediaPlayer.create(context, R.raw.audio_vendedor) // Reemplaza audio_vendedor con tu archivo de audio
                    mediaPlayer?.start()
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