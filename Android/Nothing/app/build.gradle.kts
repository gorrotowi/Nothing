plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.gorro.nothing"
        minSdk = 21
        targetSdk = 31
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc01"
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
    val compose_version = "1.1.0-rc01"

    implementation( "androidx.core:core-ktx:1.7.0")
    implementation( "androidx.compose.ui:ui:$compose_version")
    implementation( "androidx.compose.material:material:$compose_version")
    implementation( "androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation( "androidx.activity:activity-compose:1.4.0")
    implementation("androidx.glance:glance-appwidget:1.0.0-alpha01")
    implementation( "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.github.thiagokimo:KonamiCode:1.1.6")

    implementation("com.squareup:seismic:1.0.3")

    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.24")

    implementation(platform("com.google.firebase:firebase-bom:29.0.4"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    implementation("com.github.jetradarmobile:android-snowfall:1.2.1")

    implementation("com.android.installreferrer:installreferrer:2.2")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    androidTestImplementation("androidx.test:core-ktx:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

detekt {
    toolVersion = "1.19.0"
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