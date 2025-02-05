plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jadermunoz.myretail"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jadermunoz.myretail"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // Forma correcta
        targetCompatibility = JavaVersion.VERSION_17  // Forma correcta
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8" // O la versión más reciente de Compose
    }
}

dependencies {
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui:1.6.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.4")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.camera:camera-core:1.3.1")
    implementation("androidx.camera:camera-view:1.3.1")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
    implementation("com.google.accompanist:accompanist-pager:0.34.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.airbnb.android:lottie-compose:6.3.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.test:runner:1.5.2")
    implementation("androidx.test:rules:1.5.0")
}