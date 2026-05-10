import com.guru.composecookbook.build.configurations.ProjectConfigs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {

    compileSdk = ProjectConfigs.compileSdkVersion
    namespace = ProjectConfigs.applicationId

    defaultConfig {
        applicationId = ProjectConfigs.applicationId
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion
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
      //  useIR = true
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"

    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfigs.kotlinCompilerExtensionVersion
    }
    lint {
        abortOnError = false
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
    implementation(project(":demos:spotify"))
    implementation(project(":demos:twitter"))
    implementation(project(":demos:tiktok"))
    implementation(project(":demos:youtube"))
    implementation(project(":demos:gmail"))
    implementation(project(":demos:datingapp"))
    implementation(project(":demos:paint"))
    implementation(project(":demos:cryptoapp:app"))
    implementation(project(":demos:moviesapp:app"))
    implementation(project(":demos:meditation"))
    implementation(project(":templates:onboarding"))
    implementation(project(":templates:paymentcard"))
    implementation(project(":templates:pinlock"))
    implementation(project(":templates:profile"))
    implementation(project(":templates:login"))
    implementation(project(":templates:cascademenu"))
    implementation(project(":components:fab"))
    implementation(project(":components:charts"))
    implementation(project(":components:tags"))
    implementation(project(":components:carousel"))
    implementation(project(":components:verticalgrid"))
    implementation(project(":components:colorpicker"))
    implementation(project(":components:comingsoon"))
    implementation(project(":animations:canvas"))
    implementation(project(":animations:lottie"))

    // Kotlin + coroutines
    implementation(libs.bundles.kotlin.main)

    // Room + Paging data layer
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.compose.runtime)

    // Compose official
    implementation(libs.bundles.compose.official)
    debugImplementation(libs.bundles.compose.debug)

    // Compose third-party
    implementation(libs.bundles.compose.third.party)

    // Third-party UI
    implementation(libs.bundles.third.party.ui)

    // Core Android
    implementation(libs.bundles.core.android)
    implementation(libs.bundles.core.android.ui)

    // Google Android
    implementation(libs.bundles.google.android)

    // Networking
    implementation(libs.bundles.networking)

    // Unit tests
    testImplementation(libs.bundles.kotlin.test)
    testImplementation(libs.bundles.junit5.test)
    testImplementation(libs.truth)

    // Instrumentation tests
    androidTestImplementation(libs.bundles.android.instrumentation.test)

    // Biometric
    implementation(libs.androidx.biometric)
}