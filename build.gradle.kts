plugins {
    id("com.android.application") version "8.8.0" apply false // O la versión más reciente
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false // O la versión más reciente
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}