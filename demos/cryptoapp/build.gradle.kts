plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.compose
    }
}

dependencies {
    implementation(project(":components:charts"))
    implementation(project(":theme"))
    implementation(project(":data"))

    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appcompat}")
    implementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")

    implementation("androidx.compose.ui:ui:${Dependencies.compose}")
    implementation("androidx.compose.material:material:${Dependencies.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Dependencies.compose}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Dependencies.constraintLayoutCompose}")
    implementation("androidx.compose.ui:ui-tooling:${Dependencies.compose}")

    implementation("androidx.paging:paging-runtime:${Dependencies.paging}")
    implementation("androidx.paging:paging-compose:${Dependencies.pagingCompose}")

    implementation("com.google.accompanist:accompanist-coil:${Dependencies.accompanistCoil}")
    implementation("com.airbnb.android:lottie:${Dependencies.lottie}")
    implementation("com.airbnb.android:lottie-compose:${Dependencies.lottieCompose}")

    implementation("androidx.lifecycle:lifecycle-extensions:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Dependencies.lifecycleViewModelCompose}")

    implementation("com.squareup.retrofit2:retrofit:${Dependencies.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Dependencies.loggingInterceptor}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.retrofitConverterGson}")

    implementation("androidx.room:room-runtime:${Dependencies.room}")
    implementation("androidx.room:room-ktx:${Dependencies.room}")
    kapt("androidx.room:room-compiler:${Dependencies.room}")
}