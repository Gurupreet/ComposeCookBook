import com.guru.composecookbook.build.dependencies.*

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
    id("org.jetbrains.kotlin.android")
}


dependencies {
    implementation(project(":components:charts"))
    implementation(project(":animations:lottie"))
    implementation(project(":demos:cryptoapp:data"))
    implementation(project(":theme"))
    implementation(project(":data"))
    implementation("androidx.wear:wear:1.2.0")

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()

    addCoreAndroidUiDependencies()
    addThirdPartyUiDependencies()

    addCoreAndroidDependencies()

    val wear_compose_version = "1.0.0-alpha07"
    implementation("androidx.wear.compose:compose-material:$wear_compose_version")

    // Foundation is additive, so you can use the mobile version in your Wear OS app.
    implementation("androidx.wear.compose:compose-foundation:$wear_compose_version")

    // If you are using Compose Navigation, use the Wear OS version (NOT THE MOBILE ONE), that is,
    // uncomment the line below and update the version number.
    implementation("androidx.wear.compose:compose-navigation:$wear_compose_version")
}
android {
    buildFeatures {
        viewBinding = true
    }
}
