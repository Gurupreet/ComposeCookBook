import com.guru.composecookbook.build.dependencies.*
plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

dependencies {
    implementation(project(":animations:canvas"))
    implementation(project(":components:comingsoon"))
    implementation(project(":data"))
    implementation(project(":theme"))

    addComposeOfficialDependencies()
    addCoreAndroidUiDependencies()
}
