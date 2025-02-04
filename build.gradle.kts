buildscript {
    val kotlin_version = "1.9.20" // Versión de Kotlin

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0") // Versión del plugin de Android
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

subprojects {
    this.buildDir = file("${rootProject.buildDir}/${project.name}")
    evaluationDependsOnChildren()
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}