plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.colorpicker"
}

dependencies {
    implementation(project(":theme"))

    implementation(libs.bundles.compose.official)
}
