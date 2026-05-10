plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.lottie"
}

dependencies {
    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.core.android)
    implementation(libs.bundles.third.party.ui)
}
