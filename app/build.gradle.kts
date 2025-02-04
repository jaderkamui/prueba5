plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jadermunoz.myretail"
    compileSdkVersion(34)

    defaultConfig {
        applicationId = "com.jadermunoz.myretail"
        minSdkVersion(24)
        targetSdkVersion(34)
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
        sourceCompatibility = JavaVersion.VERSION_1_8  // Forma correcta
        targetCompatibility = JavaVersion.VERSION_1_8  // Forma correcta
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4" // O la versión más reciente de Compose
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.10.01")) // O la versión más reciente
    implementation("androidx.compose.ui:ui")
    implementation("io.coil-kt:coil-compose:2.4.0") // O la versión más reciente
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0") // o la versión más reciente
    implementation("com.google.accompanist:accompanist-pager:0.32.0")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.camera:camera-core:1.2.0") // O la versión más reciente
    implementation("androidx.camera:camera-camera2:1.2.0") // O la versión más reciente
    implementation("androidx.camera:camera-lifecycle:1.2.0") // O la versión más reciente
    implementation("androidx.camera:camera-view:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
}