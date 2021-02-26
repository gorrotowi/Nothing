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

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
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

    implementation("com.google.firebase:firebase-core:18.0.2")
    implementation("com.google.firebase:firebase-crash:16.2.1")
    implementation("com.google.firebase:firebase-messaging:21.0.1")

    implementation("com.github.jetradarmobile:android-snowfall:1.2.0")


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