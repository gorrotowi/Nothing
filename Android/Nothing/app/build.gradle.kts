plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("io.gitlab.arturbosch.detekt") version "1.16.0-RC2"
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId("com.gorro.nothing")
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode(11)
        versionName("1.6.1")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.github.thiagokimo:KonamiCode:1.1.6")

    implementation("com.squareup:seismic:1.0.2")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.8")

    implementation(platform("com.google.firebase:firebase-bom:26.5.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("com.github.jetradarmobile:android-snowfall:1.2.0")
}

detekt {
    config = files("${rootProject.projectDir}/config/detekt/detekt.yml")

    reports {
        xml {
            enabled = true
            //destination = file("$project.reporting.baseDir/detekt.xml")
        }

        html {
            enabled = true
            //destination = file("$project.reporting.baseDir/detekt.html")
        }
    }
}

apply(plugin = "com.google.gms.google-services")