plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android {
  namespace = "com.guru.composecookbook.login"

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
  implementation(libs.bundles.compose.thirdparty)

  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.bundles.instrumentation.test)

  testImplementation(platform(libs.androidx.compose.bom))
  testImplementation(libs.bundles.compose.robolectric.test)
}
