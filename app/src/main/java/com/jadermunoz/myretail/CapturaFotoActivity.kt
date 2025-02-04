package com.jadermunoz.myretail

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File

class CapturaFotoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapturaFotoScreen()
        }
    }
}

@Composable
fun CapturaFotoScreen() {
    val context = LocalContext.current
    var imageUri: Uri? by remember { mutableStateOf(null) }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri = imageUri // No es necesario cambiar imageUri si la captura fue exitosa
            }
        }
    )

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            hasCameraPermission = isGranted
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (hasCameraPermission) {
            Button(onClick = {
                val file = File.createTempFile(
                    "image",
                    ".jpg",
                    context.cacheDir
                )
                imageUri = FileProvider.getUriForFile(
                    context,
                    "com.jadermunoz.myretail.fileprovider", // Reemplaza con tu autoridad
                    file
                )
                launcher.launch(imageUri)
            }) {
                Text("Capturar Foto")
            }
        } else {
            Button(onClick = {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }) {
                Text("Solicitar Permiso de Cámara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Foto capturada",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}