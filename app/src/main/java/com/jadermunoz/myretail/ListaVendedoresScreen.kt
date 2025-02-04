package com.jadermunoz.myretail

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jadermunoz.myretail.R

@Composable
fun ListaVendedoresScreen(navController: NavHostController) {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    val vendedores = listOf(
        Seller("Rick Sanchez", "Area 1", "user01"),
        Seller("Morty Smith", "Area 2", "user02"),
        Seller("Summer Smith", "Area 3", "user03"),
        Seller("Jerry Smith", "Area 4", "user04"),
        Seller("Beth Smith", "Area 5", "user05"),
        Seller("Otro Vendedor", "Area 6", "user06")
    )

    LazyColumn {
        items(vendedores) { vendedor ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        if (vendedor.nombre == "Summer Smith") {
                            mediaPlayer?.release()
                            mediaPlayer = MediaPlayer.create(context, R.raw.audio_vendedor)
                            mediaPlayer?.start()
                        } else {
                            mediaPlayer?.release()
                            mediaPlayer = null
                        }
                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageId = context.resources.getIdentifier(vendedor.foto, "drawable", context.packageName)
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Foto del vendedor",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Column {
                        Text(text = vendedor.nombre)
                        Text(text = vendedor.area)
                    }
                }
            }
        }
    }
    DisposableEffect(mediaPlayer) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}