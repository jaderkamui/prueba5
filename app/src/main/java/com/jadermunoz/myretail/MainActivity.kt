package com.jadermunoz.myretail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieAnimatable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            var showAppContent by remember { mutableStateOf(false) }
            var isAnimationFinished by remember { mutableStateOf(false) }
            // Cargar la animación Lottie dentro de setContent
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animacion_retail))
            val lottieAnimatable = rememberLottieAnimatable()

            LaunchedEffect(key1 = composition) {
                if (composition != null) {
                    lottieAnimatable.animate(
                        composition = composition,
                        initialProgress = 0f,
                        iterations = 1
                    )
                    isAnimationFinished = true
                    showAppContent = true
                }
            }

            // Usar MaterialTheme para el tema de la aplicación
            MaterialTheme {
                // Establecer el color de fondo del splash screen
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White), // Establecer el fondo blanco
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Contenedor para la animación Lottie
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (!showAppContent) {
                            LottieAnimation(
                                composition = composition,
                                progress = { lottieAnimatable.progress },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    // Navegación de la aplicación
                    if (showAppContent) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}