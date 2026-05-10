plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.instagram"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":theme"))

    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.compose.third.party)
    implementation(libs.bundles.core.android.ui)
}
