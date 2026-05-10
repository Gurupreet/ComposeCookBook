plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.carousel"
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":data"))

    implementation(libs.bundles.compose.official)
}
