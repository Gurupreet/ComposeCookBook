plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.gmail"
}

dependencies {
    implementation(project(":components:fab"))
    implementation(project(":data"))
    implementation(project(":theme"))

    implementation(libs.bundles.compose.official)
    implementation(libs.bundles.core.android.ui)
}
