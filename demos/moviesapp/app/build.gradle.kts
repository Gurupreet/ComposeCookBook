plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android {
  namespace = "com.guru.composecookbook.moviesapp"

  testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
  implementation(project(":components:tags"))
  implementation(project(":components:carousel"))
  implementation(project(":demos:moviesapp:data"))
  implementation(project(":theme"))
  implementation(project(":data"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.compose.thirdparty)

  implementation(libs.bundles.core.android.ui)
  implementation(libs.bundles.thirdparty.ui)

  implementation(libs.bundles.core.android)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.compose.robolectric.test)
}
