plugins {
  /** See [common-kotlin-module-configs-script-plugin.gradle.kts] file */
  id("common-kotlin-module-configs-script-plugin")
}

android { namespace = "com.guru.composecookbook.moviesapp.data" }

dependencies {
  implementation(libs.bundles.core.android)
  implementation(libs.bundles.networking)
  implementation(platform(libs.androidx.compose.bom))
  ksp(libs.androidx.room.compiler)
  implementation(libs.bundles.data)
}
