package com.jadermunoz.myretail

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen() {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap? = result.data?.extras?.getParcelable("data", Bitmap::class.java)
            imageBitmap = bitmap
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            // Solicita permiso y lanza la cámara.
            cameraPermissionState.launchPermissionRequest()
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            launcher.launch(cameraIntent)
        }) {
            Text(text = "Capturar Foto")
        }
        Spacer(modifier = Modifier.height(16.dp))
        imageBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto Capturada",
                modifier = Modifier.size(300.dp)
            )
        }
    }
}