plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.onboarding" }

dependencies {
  implementation(project(":components:carousel"))
  implementation(project(":animations:lottie"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
}
