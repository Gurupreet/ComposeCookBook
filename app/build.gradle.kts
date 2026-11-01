import com.guru.composecookbook.build.configurations.ProjectConfigs
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.application")
  id("com.google.devtools.ksp")
  id("org.jetbrains.kotlin.plugin.compose")
  alias(libs.plugins.roborazzi)
}

android {
  compileSdk = ProjectConfigs.compileSdkVersion
  namespace = ProjectConfigs.applicationId

  defaultConfig {
    applicationId = ProjectConfigs.applicationId
    minSdk = ProjectConfigs.minSdkVersion
    targetSdk = ProjectConfigs.targetSdkVersion
    versionCode = 2
    versionName = "2.0.0"
    multiDexEnabled = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  // Compose 1.12 (BOM 2026.09.00) ships inline bytecode built with JVM target 11,
  // so the app module must target at least JVM 11 (targeting 17 to match the library modules).
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  buildFeatures { compose = true }
  lint { abortOnError = false }
  // useJUnitPlatform: unit tests run on the JUnit Platform (Jupiter engine for
  // the existing tests, Vintage engine for the Robolectric JUnit 4 screenshot
  // tests). isIncludeAndroidResources is required for Roborazzi/Robolectric to
  // render Compose with real resources on the JVM.
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      all { it.useJUnitPlatform() }
    }
  }
}

// AGP 9.0 ships built-in Kotlin (replacing the kotlin-android plugin), so the
// JVM target is configured through the Kotlin compiler options.
kotlin { compilerOptions { jvmTarget.set(JvmTarget.JVM_17) } }

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

  implementation(libs.bundles.kotlin)

  implementation(platform(libs.androidx.compose.bom))
  ksp(libs.androidx.room.compiler)
  implementation(libs.bundles.data)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  debugImplementation(platform(libs.androidx.compose.bom))
  debugImplementation(libs.bundles.compose.debug)
  implementation(libs.bundles.compose.thirdparty)

  implementation(libs.bundles.thirdparty.ui)

  implementation(libs.bundles.core.android)
  implementation(libs.bundles.core.android.ui)
  implementation(libs.bundles.google.android)
  implementation(libs.bundles.networking)

  testImplementation(libs.bundles.kotlin.test)
  testImplementation(libs.bundles.junit5.test)
  testImplementation(libs.truth)

  // Roborazzi screenshot tests run under Robolectric (JUnit 4). This module's
  // unit tests use the JUnit Platform (useJUnitPlatform), so the JUnit Vintage
  // engine is required for the Robolectric JUnit 4 runner to be discovered.
  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.screenshot.test)
  testImplementation(libs.bundles.compose.robolectric.test)
  testImplementation(libs.junit.vintage.engine)

  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.bundles.instrumentation.test)
  implementation(libs.androidx.biometric)
}
