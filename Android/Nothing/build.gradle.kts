buildscript {
    val kotlin_version = "1.5.20"

    repositories {
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0-alpha07")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    repositories {
        google()
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
