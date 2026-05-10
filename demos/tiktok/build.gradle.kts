plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.tiktok"
}

dependencies {
    implementation(project(":components:carousel"))
    implementation(project(":components:verticalgrid"))
    implementation(project(":data"))
    implementation(project(":theme"))

    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.core.android.ui)
    implementation(libs.bundles.third.party.ui)
    implementation(libs.bundles.google.android)
}
