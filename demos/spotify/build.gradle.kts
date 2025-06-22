import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.spotify"
}

dependencies {
    implementation(project(":components:verticalgrid"))
    implementation(project(":data"))
    implementation(project(":theme"))

    addComposeOfficialDependencies()
    addCoreAndroidUiDependencies()
}
