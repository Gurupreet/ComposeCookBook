plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.pinlock"
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":data"))
    implementation(project(":animations:lottie"))
    implementation(project(":components:tags"))
    implementation(project(":templates:onboarding"))

    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.compose.third.party)
    implementation(libs.androidx.biometric)
}