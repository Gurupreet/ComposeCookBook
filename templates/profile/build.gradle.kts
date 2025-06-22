import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.profile"
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":data"))
    implementation(project(":components:tags"))

    addComposeOfficialDependencies()
}