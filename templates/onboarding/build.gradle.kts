plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android {
  namespace = "com.guru.composecookbook.onboarding"

  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":components:carousel"))
  implementation(project(":animations:lottie"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.compose.robolectric.test)
}
