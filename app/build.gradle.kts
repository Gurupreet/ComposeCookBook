plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.guru.composecookbook"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.compileSdk
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.compose
    }
    lint {
        isAbortOnError = false
    }
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":theme"))
    implementation(project(":demos:instagram"))
    implementation(project(":demos:cryptoapp"))
    implementation(project(":components:charts"))

    debugImplementation("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.kotlin}")

    implementation("com.github.Gurupreet:FontAwesomeCompose:${Dependencies.fontAwesomeCompose}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Dependencies.kotlin}")
    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appcompat}")
    implementation("androidx.palette:palette-ktx:${Dependencies.paletteKtx}")
    implementation("com.google.android.material:material:${Dependencies.material}")
    implementation("androidx.multidex:multidex:${Dependencies.multidex}")

    //google play services
    implementation("com.google.android.gms:play-services-ads:${Dependencies.playServicesAds}")
    implementation("com.google.android.libraries.maps:maps:${Dependencies.googleMaps}")
    implementation("com.google.android.gms:play-services-maps:${Dependencies.playServicesMaps}")

    //compose libs
    implementation("androidx.compose.ui:ui:${Dependencies.compose}")
    implementation("androidx.compose.material:material:${Dependencies.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Dependencies.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Dependencies.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Dependencies.compose}")

    // Custom fling behaviour
    // refer to https://github.com/iamjosephmj/flinger for more insights on the library
    implementation("com.github.iamjosephmj:flinger:${Dependencies.flinger}")

    //paging
    implementation("androidx.paging:paging-runtime:${Dependencies.paging}")
    // Jetpack Compose Integration
    implementation("androidx.paging:paging-compose:${Dependencies.pagingCompose}")

    // Added starting from compose alpha-12
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Dependencies.lifecycleViewModelCompose}")
    implementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Dependencies.constraintLayoutCompose}")

    //lottie
    implementation("com.airbnb.android:lottie:${Dependencies.lottie}")
    //lottie for compose
    implementation("com.airbnb.android:lottie-compose:${Dependencies.lottieCompose}")

    implementation("com.google.android.exoplayer:exoplayer:${Dependencies.exoplayer}")
    //Network libs
    // Room
    implementation("androidx.room:room-runtime:${Dependencies.room}")
    implementation("androidx.room:room-ktx:${Dependencies.room}")
    kapt("androidx.room:room-compiler:${Dependencies.room}")
    // annotationProcessor "android.arch.persistence.room:compiler:$room_version"

    implementation("com.google.accompanist:accompanist-coil:${Dependencies.accompanistCoil}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycleRuntimeKtx}")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Dependencies.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Dependencies.loggingInterceptor}")
    implementation("com.google.code.gson:gson:${Dependencies.gson}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.retrofitConverterGson}")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.coroutine}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Dependencies.coroutine}")
    //lifecycle + viewmodel
    implementation("androidx.lifecycle:lifecycle-extensions:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.androidLifecycleGrouped}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Dependencies.junitJupiterApi}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${Dependencies.junitJupiterEngine}")
    testImplementation("com.google.truth:truth:${Dependencies.truth}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:${Dependencies.kotlin}")
    testImplementation("org.jetbrains.kotlin:kotlin-test:${Dependencies.kotlin}")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Dependencies.compose}")
    androidTestImplementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    androidTestImplementation("androidx.test.ext:junit:${Dependencies.androidXJunit}")

    implementation("androidx.navigation:navigation-compose:${Dependencies.navCompose}")

}