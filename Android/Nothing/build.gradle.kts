buildscript {
    val kotlin_version = "1.6.10"

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0-beta01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    repositories {
        google()
        maven(url = "https://jitpack.io")
    }
}
