plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.cryptoapp.data"
}

dependencies {
    implementation(libs.bundles.core.android)
    implementation(libs.bundles.networking)

    // Room + Paging data layer
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.compose.runtime)
}
