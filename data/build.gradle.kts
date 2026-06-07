plugins {
  /** See [common-kotlin-module-configs-script-plugin.gradle.kts] file */
  id("common-kotlin-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.data" }

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.compose.runtime)
}
