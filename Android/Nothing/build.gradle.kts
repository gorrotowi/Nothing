buildscript {
    val kotlin_version = "1.6.10"

    repositories {
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.3.8")
    }
}

allprojects {
    repositories {
        google()
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
