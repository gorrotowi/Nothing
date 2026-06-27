buildscript {
    val kotlin_version = "2.0.21"

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.7.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.4.2")
    }
}

allprojects {
    repositories {
        google()
        maven(url = "https://jitpack.io")
    }
}
