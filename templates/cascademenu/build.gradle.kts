import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addComposeThirdPartyDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":data"))
    implementation(project(":components:tags"))

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()
}