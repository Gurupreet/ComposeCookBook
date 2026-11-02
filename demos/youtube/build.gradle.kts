plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android {
  namespace = "com.guru.composecookbook.youtube"

  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":data"))
  implementation(project(":theme"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.core.android.ui)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.compose.robolectric.test)
}
