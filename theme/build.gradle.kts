plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.compileSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.compose
    }
}

dependencies {
    implementation("com.google.android.material:material:${Dependencies.material}")

    api("com.github.Gurupreet:FontAwesomeCompose:${Dependencies.fontAwesomeCompose}")
    implementation("androidx.compose.ui:ui:${Dependencies.compose}")
    implementation("androidx.compose.material:material:${Dependencies.compose}")
}