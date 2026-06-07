plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.verticalgrid" }

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
}
