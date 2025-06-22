import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.meditation"
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":components:verticalgrid"))

    addComposeOfficialDependencies()
    addCoreAndroidUiDependencies()
}
