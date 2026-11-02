plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android {
  namespace = "com.guru.composecookbook.cascademenu"

  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":theme"))
  implementation(project(":data"))
  implementation(project(":components:tags"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.compose.thirdparty)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.compose.robolectric.test)
}
