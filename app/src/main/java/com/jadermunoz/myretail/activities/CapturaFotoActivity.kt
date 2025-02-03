package com.jaderkamui.prueba5

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.ByteBuffer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class CapturaFotoActivity : ComponentActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var imageCapture: ImageCapture
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CameraPreview(
                onCapturePhoto = ::takePhoto,
                onSwitchCamera = ::switchCamera,
                modifier = Modifier.fillMaxSize()
            )
        }

        startCamera()
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Configurar vista previa
            val preview = Preview.Builder().build()
                .also { it.setSurfaceProvider(previewView.surfaceProvider) }

            // Configurar captura de imagen
            imageCapture = ImageCapture.Builder()
                .setTargetResolution(Size(640, 480)) // Ajusta la resolución según tus necesidades
                .build()

            // Seleccionar cámara
            val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

            try {
                // Desvincular usos anteriores
                cameraProvider.unbindAll()

                // Vincular cámara y vista previa
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                // Log or handle any errors
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageFile = File(externalMediaDirs.first(), "captured_image.jpg")

        val outputOptions = androidx.camera.core.ImageCapture.OutputFileOptions(imageFile)

        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(this@CapturaFotoActivity, "Foto guardada en: ${imageFile.absolutePath}", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: androidx.camera.core.ImageCaptureException) {
                Toast.makeText(this@CapturaFotoActivity, "Error al capturar la foto: ${exception.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun switchCamera() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        startCamera()
    }

    @Composable
    fun CameraPreview(
        onCapturePhoto: () -> Unit,
        onSwitchCamera: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val context = LocalContext.current
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Column(modifier = modifier) {
                AndroidView(
                    factory = {
                        PreviewView(it).also {
                            previewView = it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onSwitchCamera) {
                        Text("Cambiar Cámara")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onCapturePhoto) {
                        Text("Capturar")
                    }
                }
            }
        }
    } else {
        // Solicitar permisos
        ActivityCompat.requestPermissions(
            context as CapturaFotoActivity,
            arrayOf(Manifest.permission.CAMERA),
            101 // Código de solicitud de permiso
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == 101) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()    // Rewind the buffer to the beginning
    val bytes = ByteArray(remaining()) // Create a byte array of the same size as the buffer
    get(bytes)      // Copy the buffer into the byte array
    return bytes
}
}