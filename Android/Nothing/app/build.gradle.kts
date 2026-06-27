plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gorro.nothing"
        minSdk = 21
        targetSdk = 35
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

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    lint {
        baseline = file("config/lint/baseline.xml")
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation(platform("androidx.compose:compose-bom:2026.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.glance:glance-appwidget:1.1.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.github.thiagokimo:KonamiCode:1.1.6")

    implementation("com.squareup:seismic:1.0.3")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.24")

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    implementation("com.github.jetradarmobile:android-snowfall:1.2.1")

    implementation("com.android.installreferrer:installreferrer:2.2")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit-ktx:1.2.1")
    androidTestImplementation("androidx.test:core-ktx:1.6.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

detekt {
    toolVersion = "1.23.0"
    buildUponDefaultConfig = true

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