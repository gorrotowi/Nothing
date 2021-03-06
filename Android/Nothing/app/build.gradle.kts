plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("io.gitlab.arturbosch.detekt") version "1.16.0-RC2"
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.gorro.nothing"
        minSdk = 16
        targetSdk = 30
        versionCode = 11
        versionName = "1.6.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
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

    implementation("com.github.jetradarmobile:android-snowfall:1.2.0")

    implementation("com.android.installreferrer:installreferrer:2.2")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.2")
    androidTestImplementation("androidx.test:core-ktx:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
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