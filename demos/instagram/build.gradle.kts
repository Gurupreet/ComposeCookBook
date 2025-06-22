import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addComposeThirdPartyDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies

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

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()
    addCoreAndroidUiDependencies()
}
