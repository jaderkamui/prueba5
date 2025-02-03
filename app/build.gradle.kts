plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    // ... (configuración anterior)

    buildFeatures {
        compose = true
    }

    composeOptions {
        // No es necesario especificar la versión aquí, se gestiona con el compose-bom
    }

    // ... (configuración anterior)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24") // Versión consistente de Kotlin

    implementation(platform("androidx.compose:compose-bom:2023.08.00")) // Versión consistente de Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Dependencias agregadas
    implementation("com.airbnb.android:lottie-compose:6.0.0") // Lottie para animaciones
    implementation("io.coil-kt:coil-compose:2.3.0") // Coil para cargar imágenes
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // MPAndroidChart para gráficos
    implementation("androidx.camera:camera-core:1.2.0") // Cámara
    implementation("androidx.camera:camera-camera2:1.2.0") // Cámara
    implementation("androidx.camera:camera-lifecycle:1.2.0") // Cámara
    implementation("androidx.camera:camera-view:1.2.0") // Cámara
    implementation("androidx.activity:activity-ktx:1.7.2") // Para la captura de fotos
    implementation("androidx.media3:media3-exoplayer:1.2.0") // Para la reproducción de audio

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}