plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.cascademenu" }

dependencies {
  implementation(project(":theme"))
  implementation(project(":data"))
  implementation(project(":components:tags"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.compose.thirdparty)
}
