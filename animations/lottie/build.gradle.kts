import com.guru.composecookbook.build.dependencies.*

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

dependencies {
    addComposeOfficialDependencies()
    addCoreAndroidDependencies()
    addThirdPartyUiDependencies()
}
