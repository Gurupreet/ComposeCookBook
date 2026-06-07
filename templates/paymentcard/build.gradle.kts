plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.paymentcard" }

dependencies {
  implementation(project(":theme"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
}
