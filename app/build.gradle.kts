import com.guru.composecookbook.build.configurations.ProjectConfigs
import com.guru.composecookbook.build.dependencies.addAndroidInstrumentationTestsDependencies
import com.guru.composecookbook.build.dependencies.addBiometricDependency
import com.guru.composecookbook.build.dependencies.addComposeDebugDependencies
import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addComposeThirdPartyDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies
import com.guru.composecookbook.build.dependencies.addDataDependencies
import com.guru.composecookbook.build.dependencies.addGoogleAndroidDependencies
import com.guru.composecookbook.build.dependencies.addJunit5TestDependencies
import com.guru.composecookbook.build.dependencies.addKotlinDependencies
import com.guru.composecookbook.build.dependencies.addKotlinTestDependencies
import com.guru.composecookbook.build.dependencies.addNetworkingDependencies
import com.guru.composecookbook.build.dependencies.addThirdPartyUiDependencies
import com.guru.composecookbook.build.dependencies.addThirdPartyUnitTestsDependencies

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

    addKotlinDependencies()

    addDataDependencies()

    addComposeOfficialDependencies()
    addComposeDebugDependencies()
    addComposeThirdPartyDependencies()

    addThirdPartyUiDependencies()

    addCoreAndroidDependencies()
    addCoreAndroidUiDependencies()
    addGoogleAndroidDependencies()
    addNetworkingDependencies()

    addKotlinTestDependencies()
    addJunit5TestDependencies()
    addThirdPartyUnitTestsDependencies()

    addAndroidInstrumentationTestsDependencies()
    addBiometricDependency()
}