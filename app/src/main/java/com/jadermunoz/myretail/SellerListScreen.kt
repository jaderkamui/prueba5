package com.jadermunoz.myretail

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import com.jadermunoz.myretail.model.Seller

@Composable
fun SellerListScreen(navController: NavController) {
    val context = LocalContext.current

    // Lista de vendedores: personajes de Rick y Morty
    val sellers = listOf(
        Seller(1, "Rick Sanchez", "Científico", 100f, R.drawable.user01),
        Seller(2, "Morty Smith", "Aventurero", 80f, R.drawable.user02),
        Seller(3, "Summer Smith", "Marketing", 120f, R.drawable.user03),
        Seller(4, "Beth Smith", "Gerencia", 110f, R.drawable.user04),
        Seller(5, "Jerry Smith", "Ventas", 70f, R.drawable.user05),
        Seller(6, "Mr. Meeseeks", "Soporte", 90f, R.drawable.user06)
    )

    // Inicializa el MediaPlayer para reproducir audio al tocar el tercer vendedor
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    DisposableEffect(key1 = context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.audio_premio)
        onDispose { mediaPlayer?.release() }
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)) {
        itemsIndexed(sellers) { index, seller ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        // Si se toca el tercer vendedor (índice 2) se reproduce el audio.
                        if (index == 2) {
                            mediaPlayer?.start()
                        }
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = seller.photo),
                        contentDescription = "Foto de ${seller.name}",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = seller.name)
                        Text(text = seller.department)
                    }
                }
            }
        }
    }
}
