plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.paymentcard"
}

dependencies {
    implementation(project(":theme"))

    implementation(libs.bundles.compose.official)
}