plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
  id("org.jetbrains.kotlin.android")
}

android { namespace = "com.guru.composecookbook.cryptoapp" }

dependencies {
  implementation(project(":components:charts"))
  implementation(project(":animations:lottie"))
  implementation(project(":demos:cryptoapp:data"))
  implementation(project(":theme"))
  implementation(project(":data"))
  // Wear OS (compose-material here is the Wear variant, not the mobile one)
  implementation(libs.bundles.wear)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.compose.thirdparty)

  implementation(libs.bundles.core.android.ui)
  implementation(libs.bundles.thirdparty.ui)

  implementation(libs.bundles.core.android)
}

android { buildFeatures { viewBinding = true } }
