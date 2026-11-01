plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
  alias(libs.plugins.roborazzi)
}

android {
  namespace = "com.guru.composecookbook.pinlock"

  // Roborazzi renders Compose under Robolectric on the JVM; it needs Android resources.
  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":theme"))
  implementation(project(":data"))
  implementation(project(":animations:lottie"))
  implementation(project(":components:tags"))
  implementation(project(":templates:onboarding"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.screenshot.test)
  testImplementation(libs.bundles.compose.robolectric.test)
  implementation(libs.bundles.compose.thirdparty)
  implementation(libs.androidx.biometric)
}
