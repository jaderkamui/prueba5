package com.jadermunoz.myretail.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File

class CapturaFotoActivity : ComponentActivity() {
    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var hasCameraPermission by remember {
                mutableStateOf(
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                )
            }
            var showConfirmPhotoScreen by remember { mutableStateOf(false) }
            var currentImageUri by remember { mutableStateOf<Uri?>(null) }

            val requestPermissionLauncher =
                rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted ->
                    hasCameraPermission = isGranted
                    if (isGranted) {
                        launchCamera(context)
                    }
                }

            val takePictureLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Obtenemos la Uri de la foto del Intent de resultado
                    val uri = imageUri
                    uri?.let {
                        currentImageUri = it
                        showConfirmPhotoScreen = true
                    }
                }
            }

            // Usamos LaunchedEffect para lanzar la cámara después de que el launcher esté inicializado
            LaunchedEffect(hasCameraPermission) {
                if (hasCameraPermission) {
                    if (!showConfirmPhotoScreen) {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        imageUri = getOutputUri(context)
                        imageUri?.let {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, it)
                            takePictureLauncher.launch(intent)
                        }
                    }
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }

            if (showConfirmPhotoScreen) {
                currentImageUri?.let { uri ->
                    ConfirmPhotoScreen(
                        imageUri = uri,
                        onUsePhoto = {
                            val resultIntent = Intent().apply {
                                putExtra(EXTRA_IMAGE_URI, uri.toString())
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        },
                        onRetakePhoto = {
                            showConfirmPhotoScreen = false
                            // LaunchedEffect se encargará de volver a lanzar la cámara
                        }
                    )
                }
            }
        }
    }

    private fun launchCamera(context: android.content.Context) {
        // No necesitamos hacer nada aquí, la lógica está en LaunchedEffect
    }

    private fun getOutputUri(context: android.content.Context): Uri? {
        val file = File.createTempFile(
            "image",
            ".jpg",
            context.cacheDir
        )
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }
}

@Composable
fun ConfirmPhotoScreen(imageUri: Uri, onUsePhoto: () -> Unit, onRetakePhoto: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "Foto capturada",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = onUsePhoto) {
                Text("Usar esta foto")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onRetakePhoto) {
                Text("Tomar otra")
            }
        }
    }
}