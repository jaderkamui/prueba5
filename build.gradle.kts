plugins {
    id("com.android.application") version "8.1.4" apply false // O la versión más reciente
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false // O la versión más reciente
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}