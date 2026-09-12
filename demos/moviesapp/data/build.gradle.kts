import java.util.Properties

plugins {
  /** See [common-kotlin-module-configs-script-plugin.gradle.kts] file */
  id("common-kotlin-module-configs-script-plugin")
}

// TMDB API key. Put `tmdbApiKey=<your key>` in local.properties (git-ignored) or
// export TMDB_API_KEY. Never commit the key; get one at https://www.themoviedb.org/settings/api
val tmdbApiKey: String = run {
  val props = Properties()
  val localProperties = rootProject.file("local.properties")
  if (localProperties.exists()) localProperties.inputStream().use { props.load(it) }
  props.getProperty("tmdbApiKey")?.trim('"')?.takeIf { it.isNotBlank() }
    ?: System.getenv("TMDB_API_KEY")
    ?: ""
}

android {
  namespace = "com.guru.composecookbook.moviesapp.data"

  buildFeatures { buildConfig = true }

  defaultConfig { buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"") }
}

dependencies {
  implementation(libs.bundles.core.android)
  implementation(libs.bundles.networking)
  implementation(platform(libs.androidx.compose.bom))
  ksp(libs.androidx.room.compiler)
  implementation(libs.bundles.data)
}
