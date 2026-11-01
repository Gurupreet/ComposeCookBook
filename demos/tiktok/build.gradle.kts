plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
  alias(libs.plugins.roborazzi)
  id("org.jetbrains.kotlin.plugin.serialization")
}

android {
  namespace = "com.guru.composecookbook.tiktok"

  // Roborazzi renders Compose under Robolectric on the JVM; it needs Android resources.
  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":components:carousel"))
  implementation(project(":components:verticalgrid"))
  implementation(project(":data"))
  implementation(project(":theme"))

  implementation(libs.bundles.kotlin)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.screenshot.test)
  testImplementation(libs.bundles.compose.robolectric.test)
  implementation(libs.bundles.core.android.ui)
  implementation(libs.bundles.thirdparty.ui)
  implementation(libs.bundles.google.android)
}
