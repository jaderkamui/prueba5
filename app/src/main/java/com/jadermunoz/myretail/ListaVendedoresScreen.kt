package com.jadermunoz.myretail

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jadermunoz.myretail.activities.CapturaFotoActivity

data class Vendedor(
    val nombre: String,
    val area: String,
    var foto: Int,
    val ventas: Float,
    var fotoUri: Uri? = null
)

@Composable
fun ListaVendedoresScreen(navController: NavController) {
    val context = LocalContext.current
    // Crear el MediaPlayer fuera del remember para que se cree una sola vez
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.audio_vendedor) }
    var selectedVendedorIndex by remember { mutableStateOf(-1) }

    val vendedores = remember {
        mutableStateListOf(
            Vendedor("Rick Sanchez", "Dimensión C-137", R.drawable.user01, 1000f),
            Vendedor("Morty Smith", "Dimensión C-137", R.drawable.user02, 1200f),
            Vendedor("Summer Smith", "Dimensión C-137", R.drawable.user03, 1500f),
            Vendedor("Jerry Smith", "Dimensión C-137", R.drawable.user04, 800f),
            Vendedor("Beth Smith", "Dimensión C-137", R.drawable.user05, 900f)
        )
    }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uriString = result.data?.getStringExtra(CapturaFotoActivity.EXTRA_IMAGE_URI)
            uriString?.let { uri ->
                val selectedIndex = selectedVendedorIndex
                if (selectedIndex != -1) {
                    vendedores[selectedIndex] =
                        vendedores[selectedIndex].copy(fotoUri = Uri.parse(uri), foto = 0)
                }
            }
        }
    }

    // Liberar el MediaPlayer cuando el composable se desmonte
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(vendedores) { index, vendedor ->
                VendedorItem(
                    vendedor = vendedor,
                    isSelected = selectedVendedorIndex == index,
                    onVendedorClick = {
                        selectedVendedorIndex = index
                        if (index == 2) { // Tercer vendedor (índice 2)
                            mediaPlayer.start()
                        }
                    },
                    onTakePhotoClick = {
                        selectedVendedorIndex = index
                        val intent = Intent(context, CapturaFotoActivity::class.java)
                        takePictureLauncher.launch(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun VendedorItem(
    vendedor: Vendedor,
    isSelected: Boolean,
    onVendedorClick: () -> Unit,
    onTakePhotoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onVendedorClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (vendedor.fotoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(vendedor.fotoUri),
                    contentDescription = "Foto de ${vendedor.nombre}",
                    modifier = Modifier.size(50.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = vendedor.foto),
                    contentDescription = "Foto de ${vendedor.nombre}",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = vendedor.nombre)
                Text(text = vendedor.area)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$${vendedor.ventas}")
        }
        if (isSelected) {
            Button(onClick = onTakePhotoClick) {
                Text("Tomar foto")
            }
        }
    }
}