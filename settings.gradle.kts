pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.3.0" apply false // O la versión más reciente
        id("org.jetbrains.kotlin.android") version "1.9.20" apply false // O la versión más reciente
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Agrega esta línea
    }
}

rootProject.name = "jader_munoz_seccioncurso5"
include(":app")