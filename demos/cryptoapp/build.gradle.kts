import com.guru.composecookbook.build.dependencies.*

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}


dependencies {
    implementation(project(":components:charts"))
    implementation(project(":theme"))
    implementation(project(":data"))
    implementation(project(":demos:cryptoapp-data"))

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()

    addCoreAndroidUiDependencies()
    addThirdPartyUiDependencies()

    addCoreAndroidDependencies()
}