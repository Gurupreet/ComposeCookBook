plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.datingapp" }

dependencies {
  implementation(project(":animations:canvas"))
  implementation(project(":components:comingsoon"))
  implementation(project(":data"))
  implementation(project(":theme"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose.official)
  implementation(libs.bundles.core.android.ui)
}
