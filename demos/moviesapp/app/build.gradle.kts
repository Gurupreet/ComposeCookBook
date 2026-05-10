plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.moviesapp"
}

dependencies {
    implementation(project(":components:tags"))
    implementation(project(":components:carousel"))
    implementation(project(":demos:moviesapp:data"))
    implementation(project(":theme"))
    implementation(project(":data"))

    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.compose.third.party)

    implementation(libs.bundles.core.android.ui)
    implementation(libs.bundles.third.party.ui)

    implementation(libs.bundles.core.android)
}